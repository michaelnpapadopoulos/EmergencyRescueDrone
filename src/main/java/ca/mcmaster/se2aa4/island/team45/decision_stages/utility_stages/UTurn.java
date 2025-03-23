package ca.mcmaster.se2aa4.island.team45.decision_stages.utility_stages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.team45.decision_stages.Stage;
import ca.mcmaster.se2aa4.island.team45.decision_stages.StageManager;
import ca.mcmaster.se2aa4.island.team45.drone.commands.CommandCenter;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;

public class UTurn implements Stage {
    private final Logger logger = LogManager.getLogger();
    private boolean turned = false;
    private String turnDirection;
    private Stage returnStage;

    public UTurn(String turnDirection, Stage returnStage) {
        this.turnDirection = turnDirection;
        this.returnStage = returnStage;
    }

    @Override
    public String makeDecision(DirectionManager directionManager, CommandCenter commandCenter, StageManager stageManager) {
        if (turnDirection.equals("right")) {
            return uTurnRight(directionManager, commandCenter, stageManager);
        } else {
            return uTurnLeft(directionManager, commandCenter, stageManager);
        } 
    }

    private String uTurnRight(DirectionManager directionManager, CommandCenter commandCenter, StageManager stageManager) {
        logger.info("** Making a right U-turn **");
       
        if (turned == true) {
            stageManager.setStage(returnStage);
            commandCenter.getPreviousDecision().setPrevUTurn("right");
        }

        this.turned = true;

        return commandCenter.makeAction("heading", directionManager.getDirection().stringRight());
    }

    private String uTurnLeft(DirectionManager directionManager, CommandCenter commandCenter, StageManager stageManager) {
        logger.info("** Making a left U-turn **");

        if (turned == true) {
            stageManager.setStage(returnStage);
            commandCenter.getPreviousDecision().setPrevUTurn("left");
        }

        this.turned = true;

        return commandCenter.makeAction("heading", directionManager.getDirection().stringLeft());
    }
}
