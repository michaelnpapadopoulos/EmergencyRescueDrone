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
     * Fly the drone and echo in the current direction of the drone.
     *
     * @param previousResult the drone being used to carry out the various actions
     * @param algorithmManager 
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
     * Fly the drone and echo in the current direction of the drone.
     *
     * @param drone the drone being used to carry out the various actions
     * @param direction the current direction of the drone.
     * @param decision the decision JSON object to be modified
     * @param parameters the parameter JSON object that stores the additional
     * parameters for the action
     **************************************************************************/
    public void initialize(DroneConfiguration config) { 
        // Add all observers to the observer list
        addStatusObserver(new POIObserver());
        addStatusObserver(new BatteryObserver());
        addStatusObserver(new CoordinateObserver()); // Need to adjust coordinates before direction
        addStatusObserver(new DirectionObserver());
        
        // Initialize the drone status with the configuration information        
        droneStatus.getDirectionManager().setDirection(config.getDirection());
        previousResult.setPreviousResult(0, null, new JSONObject()); // Initialize previous result before any decisions are made
        droneStatus.getBatteryManager().setBatteryLevel(config.getBatteryManager().getBatteryLevel());
    }

    /**************************************************************************
     * Fly the drone and echo in the current direction of the drone.
     *
     * @param drone the drone being used to carry out the various actions
     * @param direction the current direction of the drone.
     * @param decision the decision JSON object to be modified
     * @param parameters the parameter JSON object that stores the additional
     * parameters for the action
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
        
        for (StatusObserver observer: statusObservers) { // Notify all observers that a decision has been made and we have results (Pull strategy)
            observer.updateStatus(this.droneStatus, this.previousResult, this.poiManager);
        }
    }

    /**************************************************************************
     * Adds an observer to watch a status of a drone
     *
     * @param observer an observer type object which is used to update a certan
     * drone status
     **************************************************************************/
    private void addStatusObserver(StatusObserver observer) {
        statusObservers.add(observer);
    }

    /**************************************************************************
     * Fly the drone and echo in the current direction of the drone.
     *
     * @param drone the drone being used to carry out the various actions
     * @param direction the current direction of the drone.
     * @param decision the decision JSON object to be modified
     * @param parameters the parameter JSON object that stores the additional
     * parameters for the action
     **************************************************************************/
    public String getFinalReport() { // Generate final report with locations of POIs
        int reportCase = poiManager.reportCase();
        String finalReport;

        ArrayList<Creek> creeks = poiManager.getCreeks();
        Creek nearestCreek = poiManager.getNearestCreek();
        Site site = poiManager.getSite();
       
        switch (reportCase) {
            case 1:
                finalReport = String.format(
                    "Final Report: Emergency Site (%s) found at [%d,%d], nearest Creek Inlet (%s) found at [%d,%d].",
                    site.getSiteID(), site.getSiteCoordinate().getX(), site.getSiteCoordinate().getY(), 
                    nearestCreek.getCreekID(), nearestCreek.getCreekCoordinate().getX(), nearestCreek.getCreekCoordinate().getY());
                break;
            
            case 2:
                finalReport = String.format(
                    "Final Report: Emergency Site (%s) found at [%d,%d], no Creek Inlets found.",
                    site.getSiteID(), site.getSiteCoordinate().getX(), site.getSiteCoordinate().getY());
                break;

            case 3:
                finalReport = String.format(
                    "Final Report: Creek Inlet (%s) found at [%d,%d].",
                    creeks.get(0).getCreekID(), creeks.get(0).getCreekCoordinate().getX(), creeks.get(0).getCreekCoordinate().getY());
                break;

            default:
                finalReport = "No POIs Found.";
                break;
        }

        return finalReport;
    }
}