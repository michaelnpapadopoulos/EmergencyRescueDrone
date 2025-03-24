package ca.mcmaster.se2aa4.island.team45.flight_algorithm.utility_stages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.team45.drone.commands.CommandCenter;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.flight_algorithm.*;

public class InPositionTurn implements Stage {
    private final Logger logger = LogManager.getLogger();
    private int turnCount = 0;
    private final String turnDirection;
    private final Stage returnStage;

    /**************************************************************************
     * In position turn constructor
     * 
     * @param turnDirection the direction "right","left" to in position turn in
     * @param returnStage the stage to move to when the UTurn ends
    **************************************************************************/
    public InPositionTurn(String turnDirection, Stage returnStage) {
        this.turnDirection = turnDirection;
        this.returnStage = returnStage;
    }

    /**************************************************************************
     * Determines whether to move the drone one coordinate to its relative
     * right or left
     * 
     * @param directionManager the drones direction manager object
     * @param commandCenter the drones command center object
     * @param algorithmManager the programs algorithm manager
    **************************************************************************/
    @Override
    public String makeDecision(DirectionManager directionManager, CommandCenter commandCenter, AlgorithmManager algorithmManager) {
        if (turnDirection.equals("right")) {
            return inPositionRight(directionManager, commandCenter, algorithmManager);
        } else if (turnDirection.equals("left")) {
            return inPositionLeft(directionManager, commandCenter, algorithmManager);
        } else {
            logger.error("** Invalid turn direction **");
            return null;
        }
    }

    /**************************************************************************
     * Moves the drone one coordinate to its relative right
     * 
     * @param directionManager the drones direction manager object
     * @param commandCenter the drones command center object
     * @param algorithmManager the programs algorithm manager
    **************************************************************************/
    private String inPositionRight(DirectionManager directionManager, CommandCenter commandCenter, AlgorithmManager algorithmManager) {
        logger.info("** Making an in position right turn **");
        String headingDir;

        if (turnCount == 4) { // Final right turn to face the initial direction
            algorithmManager.setStage(returnStage);
            return commandCenter.makeAction("heading", directionManager.getDirection().getRight());
        }

        if (turnCount == 0) {
            turnCount++;
            headingDir = directionManager.getDirection().getRight();
            
        } else if (turnCount == 1) { // Offsets plane by 1 square to allow for an in position right turn
            turnCount++;
            return commandCenter.makeAction("fly");

        } else if (turnCount == 2) {
            turnCount++;
            headingDir = directionManager.getDirection().getRight();

        } else {
            turnCount++;
            headingDir = directionManager.getDirection().getRight();
        }
        
        return commandCenter.makeAction("heading", headingDir);
    }

    /**************************************************************************
     * Moves the drone one coordinate to its relative left
     * 
     * @param directionManager the drones direction manager object
     * @param commandCenter the drones command center object
     * @param algorithmManager the programs algorithm manager
    **************************************************************************/
    private String inPositionLeft(DirectionManager directionManager, CommandCenter commandCenter, AlgorithmManager algorithmManager) {
        logger.info("** Making an in position left turn **");
        String headingDir;

        if (turnCount == 4) { // Final left turn to face the initial direction
            algorithmManager.setStage(returnStage);
            return commandCenter.makeAction("heading", directionManager.getDirection().getLeft());
        }

        if (turnCount == 0) {
            turnCount++;
            headingDir = directionManager.getDirection().getLeft();
            
        } else if (turnCount == 1) { // Offsets plane by 1 square to allow for an in position left turn
            turnCount++;
            return commandCenter.makeAction("fly");

        } else if (turnCount == 2) {
            turnCount++;
            headingDir = directionManager.getDirection().getLeft();

        } else {
            turnCount++;
            headingDir = directionManager.getDirection().getLeft();
        }
        
        return commandCenter.makeAction("heading", headingDir);
    }
}
