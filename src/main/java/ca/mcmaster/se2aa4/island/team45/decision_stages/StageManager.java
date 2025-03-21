package ca.mcmaster.se2aa4.island.team45.decision_stages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.team45.decision_stages.finding_island.FirstEcho;
import ca.mcmaster.se2aa4.island.team45.drone.PreviousState;
import ca.mcmaster.se2aa4.island.team45.drone.battery.BatteryManager;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.map.POIManager;
import ca.mcmaster.se2aa4.island.team45.map.coordinates.CoordinateManager;

public class StageManager {
    private final Logger logger = LogManager.getLogger();

    private Stage currentStage;
    private CoordinateManager coordinateManager;

    public StageManager() {
        this.currentStage = new FirstEcho();
        this.coordinateManager = new CoordinateManager();
    }

    public void setStage(Stage newStage) {
        this.currentStage = newStage;
    }

    public String makeStageDecision(DirectionManager direction, BatteryManager battery, PreviousState previousState, POIManager poiManager) {
        logger.info("\n** Current Stage: {} **", currentStage.getClass().getSimpleName());
        logger.info("** Current Coords: {}", this.coordinateManager.getCoordinates().printCord());
        logger.info("** Current Direction: {}\n", direction.getDirection().stringForward());
        String dec = currentStage.makeDecision(direction, battery, previousState, this, poiManager, coordinateManager);
       
        coordinateManager.adjustCoords(direction, previousState);

        if (previousState.getPrevAction().equals("stop")) {
            logger.info("** Final battery level: {} **", battery.getBatteryLevel());
            logger.info("==== ALL POI'S FOUND ====\nNorth: {}\nEast: {}\nSouth: {}\nWest: {}", poiManager.getIslandEdge("North").printCord(), poiManager.getIslandEdge("East").printCord(), poiManager.getIslandEdge("South").printCord(), poiManager.getIslandEdge("West").printCord());
        }

        return dec;
    }
}