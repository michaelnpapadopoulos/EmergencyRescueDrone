package ca.mcmaster.se2aa4.island.team45.flight_algorithm.utility_stages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.team45.drone.commands.CommandCenter;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.flight_algorithm.*;

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
    public String makeDecision(DirectionManager directionManager, CommandCenter commandCenter, AlgorithmManager algorithmManager) {
        if (turnDirection.equals("right")) {
            return uTurnRight(directionManager, commandCenter, algorithmManager);
        } else {
            return uTurnLeft(directionManager, commandCenter, algorithmManager);
        } 
    }

    private String uTurnRight(DirectionManager directionManager, CommandCenter commandCenter, AlgorithmManager algorithmManager) {
        logger.info("** Making a right U-turn **");
       
        if (turned == true) {
            algorithmManager.setStage(returnStage);
            commandCenter.getPreviousDecision().setPrevUTurn("right");
        }

        this.turned = true;
        return commandCenter.makeAction("heading", directionManager.getDirection().getRight().toString());
    }

    private String uTurnLeft(DirectionManager directionManager, CommandCenter commandCenter, AlgorithmManager algorithmManager) {
        logger.info("** Making a left U-turn **");

        if (turned == true) {
            algorithmManager.setStage(returnStage);
            commandCenter.getPreviousDecision().setPrevUTurn("left");
        }

        this.turned = true;
        return commandCenter.makeAction("heading", directionManager.getDirection().getLeft().toString());
    }
}
