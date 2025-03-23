package ca.mcmaster.se2aa4.island.team45.decision_stages.utility_stages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.team45.decision_stages.*;
import ca.mcmaster.se2aa4.island.team45.drone.commands.CommandCenter;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;

public class InPositionTurn implements Stage {
    private final Logger logger = LogManager.getLogger();
    private int turnCount = 0;
    private String turnDirection;
    private Stage returnStage;

    public InPositionTurn(String turnDirection, Stage returnStage) {
        this.turnDirection = turnDirection;
        this.returnStage = returnStage;
    }
    
    @Override
    public String makeDecision(DirectionManager directionManager, CommandCenter commandCenter, StageManager stageManager) {
        if (turnDirection.equals("right")) {
            return inPositionRight(directionManager, commandCenter, stageManager);
        } else if (turnDirection.equals("left")) {
            return inPositionLeft(directionManager, commandCenter, stageManager);
        } else {
            logger.error("** Invalid turn direction **");
            return null;
        }
    }

    private String inPositionRight(DirectionManager direction, CommandCenter commandCenter, StageManager stageManager) {
        logger.info("** Making an in position right turn **");
        String headingDir;

        if (turnCount == 4) { // Final right turn to face the inital direction
            stageManager.setStage(returnStage);
            return commandCenter.makeAction("heading", direction.getDirection().stringRight());
        }

        if (turnCount == 0) {
            turnCount++;
            headingDir = direction.getDirection().stringRight();
            
        } else if (turnCount == 1) { // Offsets plane by 1 square to allow for an in position right turn
            turnCount++;
            return commandCenter.makeAction("fly");

        } else if (turnCount == 2) {
            turnCount++;
            headingDir = direction.getDirection().stringRight();

        } else {
            turnCount++;
            headingDir = direction.getDirection().stringRight();
        }
        
        return commandCenter.makeAction("heading", headingDir);
    }

    private String inPositionLeft(DirectionManager direction, CommandCenter commandCenter, StageManager stageManager) {
        logger.info("** Making an in position left turn **");
        String headingDir;

        if (turnCount == 4) { // Final left turn to face the inital direction
            stageManager.setStage(returnStage);
            return commandCenter.makeAction("heading", direction.getDirection().stringLeft());
        }

        if (turnCount == 0) {
            turnCount++;
            headingDir = direction.getDirection().stringLeft();
            
        } else if (turnCount == 1) { // Offsets plane by 1 square to allow for an in position left turn
            turnCount++;
            return commandCenter.makeAction("fly");

        } else if (turnCount == 2) {
            turnCount++;
            headingDir = direction.getDirection().stringLeft();

        } else {
            turnCount++;
            headingDir = direction.getDirection().stringLeft();
        }
        
        return commandCenter.makeAction("heading", headingDir);
    }
}
