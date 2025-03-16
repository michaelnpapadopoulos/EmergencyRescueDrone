package ca.mcmaster.se2aa4.island.team45.decision_stages;

import ca.mcmaster.se2aa4.island.team45.drone.BatteryManager;
import ca.mcmaster.se2aa4.island.team45.drone.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.drone.PreviousResult;
import ca.mcmaster.se2aa4.island.team45.map.CoordinateManager;
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

    public String makeStageDecision(DirectionManager direction, BatteryManager battery, PreviousResult previousResult, StageManager stageManager) {
        String decision = currentStage.makeDecision(direction, battery, previousResult, this.previousDecision, this);
        logger.info("Made a decision: {}", decision);
        if (decision.substring(11, 15).equals("stop")) {
            logger.info("Battery level at end of flight: {}", battery.getBatteryLevel());
        }
        int[] coords = this.coordinateManager.getCoordinates();
        logger.info("Current Coords: [{}, {}]", coords[0], coords[1]);
        this.coordinateManager.adjustCoords(direction, this.previousDecision);
        return decision;
    }
}
