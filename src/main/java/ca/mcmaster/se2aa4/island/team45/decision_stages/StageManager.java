package ca.mcmaster.se2aa4.island.team45.decision_stages;

import ca.mcmaster.se2aa4.island.team45.drone.BatteryManager;
import ca.mcmaster.se2aa4.island.team45.drone.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.drone.PreviousResult;
import ca.mcmaster.se2aa4.island.team45.map.CoordinateManager;
import ca.mcmaster.se2aa4.island.team45.map.POIManager;
import ca.mcmaster.se2aa4.island.team45.map.IslandEdgeManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StageManager {
    private final Logger logger = LogManager.getLogger();

    private Stage currentStage;
    private PreviousDecision previousDecision;
    private CoordinateManager coordinateManager;
    private IslandEdgeManager islandEdgeManager;

    public StageManager() {
        this.currentStage = new MissionStage();
        this.previousDecision = new PreviousDecision();
        this.coordinateManager = new CoordinateManager();
        this.islandEdgeManager = new IslandEdgeManager();
    }

    public void setStage(Stage newStage) {
        this.currentStage = newStage;
    }

    public String makeStageDecision(DirectionManager direction, BatteryManager battery, PreviousResult previousResult, StageManager stageManager, POIManager poiManager) {
        logger.info("Current stage: {}", this.currentStage.getClass().getSimpleName());
        String decision = currentStage.makeDecision(direction, battery, previousResult, this.previousDecision, this, poiManager, this.coordinateManager);
        logger.info("Made a decision: {}", decision);

        if (decision.length() >= 15 && decision.substring(11, 15).equals("stop")) {
            logger.info("Battery level at end of flight: {}", battery.getBatteryLevel());
        }

        int[] coords = this.coordinateManager.getCoordinates();
        logger.info("Current Coords: [{}, {}]", coords[0], coords[1]);

        this.coordinateManager.adjustCoords(direction, this.previousDecision);

        logger.info("Edges found:\nNorth: {}\nEast: {}\nSouth: {}\nWest: {}", poiManager.getIslandEdge("North"), poiManager.getIslandEdge("East"), poiManager.getIslandEdge("South"), poiManager.getIslandEdge("West"));
        return decision;
    }
}
