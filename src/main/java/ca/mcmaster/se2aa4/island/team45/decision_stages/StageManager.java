package ca.mcmaster.se2aa4.island.team45.decision_stages;

import ca.mcmaster.se2aa4.island.team45.drone.BatteryManager;
import ca.mcmaster.se2aa4.island.team45.drone.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.drone.PreviousResult;
import ca.mcmaster.se2aa4.island.team45.map.CoordinateManager;
import ca.mcmaster.se2aa4.island.team45.map.POIManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class StageManager {
    private final Logger logger = LogManager.getLogger();

    private Stage currentStage;
    private PreviousDecision previousDecision;
    private CoordinateManager coordinateManager;

    public StageManager() {
        this.currentStage = new InitialStage();
        this.previousDecision = new PreviousDecision();
        this.coordinateManager = new CoordinateManager();
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

    public boolean checkStop(BatteryManager bm, DirectionManager dm) {
        //checks cost to return to 1,1 and if we have enough budget within a small margin
        /*int[] coords = coordinateManager.getCoordinates();*/
        final int COST = 50;
        /*switch (dm.getDirection()) {
            case "N":
                cost = fly cost1*(coords[1] -1) + 1heading cost + 1fly cost*(coords[0] -1);
                break;
            case "E":
                cost = 1heading cost + 1fly cost*(coords[1] -1) + 1heading cost + 1fly cost*(coords[0] -1);
                break;
            case "S":
                cost = 1heading cost + 1fly cost*(coords[0] -1) + 1heading cost + 1fly cost*(coords[1] -1);
                break;
            case "W":
                cost = 1fly cost*(coords[0] -1) + 1heading cost + 1fly cost*(coords[1] -1);
                break;
        }*/
        if ((bm.getBatteryLevel()+10) <= COST/*placeholder could be changed if new max stop COST is found*/) {
            return true;
        }
        return false;
    }
}
