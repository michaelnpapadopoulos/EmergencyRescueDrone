package ca.mcmaster.se2aa4.island.team45.drone;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.team45.drone.drone_observers.*;
import ca.mcmaster.se2aa4.island.team45.flight_algorithm.AlgorithmManager;
import ca.mcmaster.se2aa4.island.team45.map.interest_points.*;

public class FlightManager {
    private final List<StatusObserver> statusObservers = new ArrayList<>();

    private final Logger logger = LogManager.getLogger();
    private final PreviousResult previousResult;
    private final AlgorithmManager algorithmManager;
    private final DroneStatus droneStatus;
    private final POIManager poiManager;


    /**************************************************************************
     * Builder pattern for the FlightManager class. This pattern is used to 
     * create a FlightManager object with the necessary parameters.
     *
     * @param builder the builder object used to create the FlightManager object
    **************************************************************************/
    private FlightManager(Builder builder) {
        this.previousResult = builder.previousResult;
        this.algorithmManager = builder.algorithmManager;
        this.droneStatus = builder.droneStatus;
        this.poiManager = builder.poiManager;
    }

    /**************************************************************************
     * Builder class for our FlightManager
     *
     * @param previousResult stores the JSONObject properties from the previous
     * action
     * @param algorithmManager moves through our algorithm for scanning the
     * island
     * @param droneStatus stores the drones current status (direction, 
     * coordinates, battery, etc.)
     * @param poiManager stores the many points of interest found while 
     * scanning and echoing
    **************************************************************************/
    public static class Builder {
        private PreviousResult previousResult;
        private AlgorithmManager algorithmManager;
        private DroneStatus droneStatus;
        private POIManager poiManager;

        public Builder withPreviousResults(PreviousResult previousResult) {
            this.previousResult = previousResult;
            return this;
        }

        public Builder withAlgorithmManager(AlgorithmManager algorithmManager) {
            this.algorithmManager = algorithmManager;
            return this;
        }

        public Builder withDroneStatus(DroneStatus droneStatus) {
            this.droneStatus = droneStatus;
            return this;
        }

        public Builder withPOIManager(POIManager poiManager) {
            this.poiManager = poiManager;
            return this;
        }

        public FlightManager build() {
            return new FlightManager(this);
        }
    }

    /**************************************************************************
     * initializes our FlightManager with the drones starting attributes
     *
     * @param direction the initial direction the drone is facing
     * @param batteryLevel the initial battery level of the drone
    **************************************************************************/
    public void initialize(String direction, int batteryLevel) { 
        // Add all observers to the observer list
        addStatusObserver(new POIObserver());
        addStatusObserver(new BatteryObserver());
        addStatusObserver(new CoordinateObserver()); // Need to adjust coordinates before direction
        addStatusObserver(new DirectionObserver());
        
        // Initialize the drones status with the given initial information       
        droneStatus.getDirectionManager().setDirection(direction);
        droneStatus.getBatteryManager().setBatteryLevel(batteryLevel);
        previousResult.setPreviousResult(0, null, new JSONObject()); // Initialize previous result before any decisions are made
    }

    /**************************************************************************
     * Takes a decision for a drone action from the algorithm manager then logs 
     * the drones status after the decision is made
    **************************************************************************/
    public String getDecision() {
        String decision = algorithmManager.makeStageDecision(
            droneStatus,
            poiManager, 
            previousResult);

        logger.info("** Current Coords: {}", droneStatus.getCoordinateManager().getCoordinates().printCord());
        logger.info("** Current Direction: {}", droneStatus.getDirectionManager().getDirection().toFullString());
        logger.info("** Current Battery Level: {}", droneStatus.getBatteryManager().getBatteryLevel());
        return decision;
    }

    /**************************************************************************
     * Acknowledge the results of the previous decision made by the drone.
     *
     * @param results the JSON object containing the results of the previous 
     * decision made by the drone
    **************************************************************************/
    public void acknowledgeResults(JSONObject results) {
        logger.info("** Acknowledging results...");
        logger.info("** Cost of previous action: {}", results.getInt("cost"));
        previousResult.setPreviousResult(results.getInt("cost"), results.getString("status"), results.getJSONObject("extras"));
        
        for (StatusObserver observer: statusObservers) { // Notify all observers that a decision has been made, and we have results (Pull strategy)
            observer.updateStatus(this.droneStatus, this.previousResult, this.poiManager);
        }
    }

    /**************************************************************************
     * Adds an observer to watch a status of a drone
     *
     * @param observer an observer type object which is used to update a certain
     * drone status
    **************************************************************************/
    private void addStatusObserver(StatusObserver observer) {
        statusObservers.add(observer);
    }

    /**************************************************************************
     * Retrieves the reportCase and formats the final report given from the 
     * search
    **************************************************************************/
    public String getFinalReport() { // Generate final report with locations of POIs
        int reportCase = poiManager.reportCase();
        String finalReport;

        ArrayList<Creek> creeks = poiManager.getCreeks();
        Creek nearestCreek = poiManager.getNearestCreek();
        Site site = poiManager.getSite();

        finalReport = switch (reportCase) {
            case 1 -> String.format(
                    "Final Report: Emergency Site (%s) found at [%d,%d], nearest Creek Inlet (%s) found at [%d,%d].",
                    site.getSiteID(), site.getSiteCoordinate().getX(), site.getSiteCoordinate().getY(),
                    nearestCreek.getCreekID(), nearestCreek.getCreekCoordinate().getX(), nearestCreek.getCreekCoordinate().getY());
            case 2 -> String.format(
                    "Final Report: Emergency Site (%s) found at [%d,%d], no Creek Inlets found.",
                    site.getSiteID(), site.getSiteCoordinate().getX(), site.getSiteCoordinate().getY());
            case 3 -> String.format(
                    "Final Report: Creek Inlet (%s) found at [%d,%d].",
                    creeks.getFirst().getCreekID(), creeks.getFirst().getCreekCoordinate().getX(), creeks.getFirst().getCreekCoordinate().getY());
            default -> "No POIs Found.";
        };

        return finalReport;
    }
}