package ca.mcmaster.se2aa4.island.team45.drone;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.team45.decision_stages.PreviousState;
import ca.mcmaster.se2aa4.island.team45.decision_stages.StageManager;
import ca.mcmaster.se2aa4.island.team45.map.POIManager;

public class FlightManager {
    private final Logger logger = LogManager.getLogger();
    private final DirectionManager directionManager;
    private final BatteryManager batteryManager;
    private final PreviousState previousState;
    private final StageManager stageManager;
    private final POIManager poiManager;

    private FlightManager(Builder builder) {
        this.directionManager = builder.directionManager;
        this.batteryManager = builder.batteryManager;
        this.previousState = builder.previousState;
        this.stageManager = builder.stageManager;
        this.poiManager = builder.poiManager;
    }

    public static class Builder {
        private DirectionManager directionManager;
        private BatteryManager batteryManager;
        private PreviousState previousState;
        private StageManager stageManager;
        private POIManager poiManager;

        public Builder withDirectionManager(DirectionManager directionManager) {
            this.directionManager = directionManager;
            return this;
        }

        public Builder withBatteryManager(BatteryManager batteryManager) {
            this.batteryManager = batteryManager;
            return this;
        }

        public Builder withPreviousState(PreviousState previousState) {
            this.previousState = previousState;
            return this;
        }

        public Builder withStageManager(StageManager stageManager) {
            this.stageManager = stageManager;
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
        directionManager.setDirection(config.getDirection());
        batteryManager.setBatteryLevel(config.getBatteryManager().getBatteryLevel());
        previousState.setPreviousResult(0, null, new JSONObject());
    }

    public String getDecision() {
        return stageManager.makeStageDecision(directionManager, batteryManager, previousState, poiManager);
    }

    public void acknowledgeResults(JSONObject results) {
        logger.info("** Acknowledging results...");
        previousState.setPreviousResult(results.getInt("cost"), results.getString("status"), results.getJSONObject("extras"));
        batteryManager.consumeBattery(previousState.getCost());
    }
}