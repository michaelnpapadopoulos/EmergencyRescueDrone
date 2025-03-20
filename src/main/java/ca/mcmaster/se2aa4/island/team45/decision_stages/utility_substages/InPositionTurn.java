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

public class InPositionTurn extends Stage {
    private final Logger logger = LogManager.getLogger();
    private int turnCount = 0;
    private String turnDirection;
    private Stage returnStage;

    public InPositionTurn(String turnDirection, Stage returnStage) {
        this.turnDirection = turnDirection;
        this.returnStage = returnStage;
    }

    private String inPositionRight(DirectionManager direction, PreviousDecision pDecision, StageManager sm) {
        logger.info("** Making an in position right turn **");
        String headingDir;

        if (turnCount == 4) { // Final right turn to face the inital direction
            pDecision.setPrevAction("heading");
            pDecision.setPrevHeading(direction.getRight());
            sm.setStage(returnStage);
            return FlightCommands.getInstance().heading(direction.getRight());
        }

        if (turnCount == 0) {
            turnCount++;
            headingDir = direction.getRight();
            
        } else if (turnCount == 1) { // Offsets plane by 1 square to allow for an in position right turn
            turnCount++;
            pDecision.setPrevAction("fly");
            return FlightCommands.getInstance().fly();

        } else if (turnCount == 2) {
            turnCount++;
            headingDir = direction.getRight();

        } else {
            turnCount++;
            headingDir = direction.getRight();
        }

        pDecision.setPrevAction("heading");
        pDecision.setPrevHeading(headingDir);
        
        return FlightCommands.getInstance().heading(headingDir);
    }



    private String inPositionLeft(DirectionManager direction, PreviousDecision pDecision, StageManager sm) {
        logger.info("** Making an in position left turn **");
        String headingDir;

        if (turnCount == 4) { // Final left turn to face the inital direction
            pDecision.setPrevAction("heading");
            pDecision.setPrevHeading(direction.getLeft());
            sm.setStage(returnStage);
            return FlightCommands.getInstance().heading(direction.getLeft());
        }

        if (turnCount == 0) {
            turnCount++;
            headingDir = direction.getLeft();
            
        } else if (turnCount == 1) { // Offsets plane by 1 square to allow for an in position left turn
            turnCount++;
            pDecision.setPrevAction("fly");
            return FlightCommands.getInstance().fly();

        } else if (turnCount == 2) {
            turnCount++;
            headingDir = direction.getLeft();

        } else {
            turnCount++;
            headingDir = direction.getLeft();
        }

        pDecision.setPrevAction("heading");
        pDecision.setPrevHeading(headingDir);
        
        return FlightCommands.getInstance().heading(headingDir);
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
                return inPositionRight(direction, pDecision, sm);
            } else if (turnDirection.equals("left")) {
                return inPositionLeft(direction, pDecision, sm);
            } else {
                logger.error("** Invalid turn direction **");
                return null;
            }
    }
}
