package ca.mcmaster.se2aa4.island.team45.decision_stages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.team45.drone.BatteryManager;
import ca.mcmaster.se2aa4.island.team45.drone.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.map.CoordinateManager;
import ca.mcmaster.se2aa4.island.team45.map.POIManager;

public class StageManager {
    private final Logger logger = LogManager.getLogger();

    private Stage currentStage;
    private PreviousState previousState;
    private CoordinateManager coordinateManager;

    public StageManager() {
        this.currentStage = new PerimeterStage();
        this.previousState = new PreviousState();
        this.coordinateManager = new CoordinateManager();
    }

    public void setStage(Stage newStage) {
        this.currentStage = newStage;
    }

    public String makeStageDecision(DirectionManager direction, BatteryManager battery, PreviousState previousState, POIManager poiManager) {
        return currentStage.makeDecision(direction, battery, previousState, this, poiManager, coordinateManager);
    }
}