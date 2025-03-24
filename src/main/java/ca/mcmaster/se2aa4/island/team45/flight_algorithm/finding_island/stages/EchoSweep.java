package ca.mcmaster.se2aa4.island.team45.flight_algorithm.finding_island.stages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.team45.drone.commands.CommandCenter;
import ca.mcmaster.se2aa4.island.team45.drone.direction.Direction;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.flight_algorithm.Stage;
import ca.mcmaster.se2aa4.island.team45.flight_algorithm.AlgorithmManager;

public class EchoSweep implements Stage {
    private final Logger logger = LogManager.getLogger();
    private boolean echoedRight = false;
    private boolean echoedLeft = false;

    /**************************************************************************
     * Echos to the left and right and then flies forward
     * 
     * @param directionManager the drones direction manager object
     * @param commandCenter the drones command center object
     * @param algorithmManager the programs algorithm manager
    **************************************************************************/
    @Override
    public String makeDecision(DirectionManager directionManager, CommandCenter commandCenter, AlgorithmManager algorithmManager) { // Echos left, forward, then right to find the edge of the island
        Direction currentDirection = directionManager.getDirection();
        String echoDirection;

        if (!echoedRight) { // Echo right
            echoedRight = true;
            echoDirection = currentDirection.getRight();
            
        } else if (!echoedLeft) { // Echo left
            echoedLeft = true;
            echoDirection = currentDirection.getLeft();

        } else {
            logger.info("** Flying forward **");
            this.echoedRight = false; this.echoedLeft = false;
            return commandCenter.makeAction("fly");
        }

        logger.info("** Echoing " + echoDirection + " **");
        return commandCenter.makeAction("echo", echoDirection);
    }
}
