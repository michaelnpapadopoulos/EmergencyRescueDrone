package ca.mcmaster.se2aa4.island.team45.decision_stages.utility_substages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.team45.decision_stages.PreviousDecision;
import ca.mcmaster.se2aa4.island.team45.decision_stages.Stage;
import ca.mcmaster.se2aa4.island.team45.decision_stages.StageManager;
import ca.mcmaster.se2aa4.island.team45.drone.BatteryManager;
import ca.mcmaster.se2aa4.island.team45.drone.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.drone.FlightCommands;
import ca.mcmaster.se2aa4.island.team45.drone.PreviousResult;
import ca.mcmaster.se2aa4.island.team45.map.POIManager;
import ca.mcmaster.se2aa4.island.team45.map.coordinates.CoordinateManager;

public class UTurn extends Stage {
    private final Logger logger = LogManager.getLogger();
    private boolean turned;
    private String turnDirection;
    private Stage returnStage;

    public UTurn(String turnDirection, Stage returnStage) {
        this.turnDirection = turnDirection;
        this.returnStage = returnStage;
    }

    public String makeDecision(
        DirectionManager direction, 
        BatteryManager battery, 
        PreviousResult pResult, 
        PreviousDecision pDecision, 
        StageManager sm, 
        POIManager poiManager, 
        CoordinateManager cm
        ) {
            if (turnDirection.equals("right")) {
                return uTurnRight(direction, pDecision, sm);
            } else {
                return uTurnLeft(direction, pDecision, sm);
            }

        }

    private String uTurnRight(DirectionManager direction, PreviousDecision pDecision, StageManager sm) {
        logger.info("** Making a right U-turn **");
        this.turned = true;

        if (turned == true) {
            sm.setStage(returnStage);
        }

        pDecision.setPrevAction("heading");
        pDecision.setPrevHeading(direction.getRight());
        return FlightCommands.getInstance().heading(direction.getRight());
    }

    private String uTurnLeft(DirectionManager direction, PreviousDecision pDecision, StageManager sm) {
        logger.info("** Making a left U-turn **");
        this.turned = true;

        if (turned == true) {
            sm.setStage(returnStage);
        }

        pDecision.setPrevAction("heading");
        pDecision.setPrevHeading(direction.getLeft());
        return FlightCommands.getInstance().heading(direction.getLeft());
    }
}
