package ca.mcmaster.se2aa4.island.team45.decision_stages.finding_island;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.team45.decision_stages.Stage;
import ca.mcmaster.se2aa4.island.team45.decision_stages.StageManager;
import ca.mcmaster.se2aa4.island.team45.drone.commands.CommandCenter;
import ca.mcmaster.se2aa4.island.team45.drone.direction.Direction;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionEnum;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;

public class EchoSweep implements Stage {
    private final Logger logger = LogManager.getLogger();
    private boolean echoedRight = false;
    private boolean echoedLeft = false;

    @Override
    public String makeDecision(DirectionManager directionManager, CommandCenter commandCenter, StageManager stageManager) { // Echos left, forward, then right to find the edge of the island
        Direction currentDirection = directionManager.getDirection();
        DirectionEnum echoDirection;

        if (echoedRight == false) { // Echo right
            echoedRight = true;
            echoDirection = DirectionEnum.fromString(currentDirection.stringRight());
            
        } else if (echoedLeft == false) { // Echo left
            echoedLeft = true;
            echoDirection = DirectionEnum.fromString(currentDirection.stringLeft());

        } else {
            logger.info("** Flying forward **");
            this.echoedRight = false; this.echoedLeft = false;
            return commandCenter.makeAction("fly");
        }

        logger.info("** Echoing " + echoDirection + " **");
        return commandCenter.makeAction("echo", echoDirection.getShortDir());
    }
}
