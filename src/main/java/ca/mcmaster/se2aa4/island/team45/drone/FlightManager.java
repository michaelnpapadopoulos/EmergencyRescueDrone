package ca.mcmaster.se2aa4.island.team45.drone;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.team45.decision_stages.StageManager;
import ca.mcmaster.se2aa4.island.team45.drone.commands.PreviousResult;
import ca.mcmaster.se2aa4.island.team45.drone.drone_observers.BatteryObserver;
import ca.mcmaster.se2aa4.island.team45.drone.drone_observers.CoordinateObserver;
import ca.mcmaster.se2aa4.island.team45.drone.drone_observers.DirectionObserver;
import ca.mcmaster.se2aa4.island.team45.drone.drone_observers.StatusObserver;
import ca.mcmaster.se2aa4.island.team45.map.interest_points.POIManager;

public class FlightManager {
    private final List<StatusObserver> statusObservers = new ArrayList<>();

    private final Logger logger = LogManager.getLogger();
    private final PreviousResult previousResult;
    private final StageManager stageManager;
    private final DroneStatus droneStatus;
    private final POIManager poiManager;

    private FlightManager(Builder builder) {
        this.previousResult = builder.previousResult;
        this.stageManager = builder.stageManager;
        this.droneStatus = builder.droneStatus;
        this.poiManager = builder.poiManager;
    }

    public static class Builder {
        private PreviousResult previousResult;
        private StageManager stageManager;
        private DroneStatus droneStatus;
        private POIManager poiManager;

        public Builder withPreviousResults(PreviousResult previousResult) {
            this.previousResult = previousResult;
            return this;
        }

        public Builder withStageManager(StageManager stageManager) {
            this.stageManager = stageManager;
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

    public void initialize(DroneConfiguration config) { 
        addStatusObserver(new BatteryObserver());
        addStatusObserver(new DirectionObserver());
        addStatusObserver(new CoordinateObserver());

        droneStatus.getDirectionManager().setDirection(config.getDirection());
        previousResult.setPreviousResult(0, null, new JSONObject());
        droneStatus.getBatteryManager().setBatteryLevel(config.getBatteryManager().getBatteryLevel());
    }

    public String getDecision() {
        String decision = stageManager.makeStageDecision(
            droneStatus,
            poiManager, 
            previousResult);
        
        for (StatusObserver observer: statusObservers) { // Notify all observers that a decision has been made (Pull strategy)
            observer.updateStatus(this.droneStatus, this.previousResult);
        }

        return decision;
    }

    public void acknowledgeResults(JSONObject results) {
        logger.info("** Acknowledging results...");
        previousResult.setPreviousResult(results.getInt("cost"), results.getString("status"), results.getJSONObject("extras"));
        droneStatus.getBatteryManager().consumeBattery(previousResult.getCost());
    }

    private void addStatusObserver(StatusObserver observer) {
        statusObservers.add(observer);
    }
}