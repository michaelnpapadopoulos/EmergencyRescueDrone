package ca.mcmaster.se2aa4.island.team45.flight_algorithm.utility_stages;

import ca.mcmaster.se2aa4.island.team45.drone.commands.CommandCenter;
import ca.mcmaster.se2aa4.island.team45.drone.direction.*;
import ca.mcmaster.se2aa4.island.team45.flight_algorithm.*;

public class Turn implements Stage { // The initial stage of the mission, find first edge of island
    String direction;
    Stage nextStage;

    /**************************************************************************
     * Turn constructor
     * 
     * @param turnDirection the direction "right","left" to Turn in
     * @param returnStage the stage to move to when the UTurn ends
    **************************************************************************/
    public Turn(String direction, Stage nextStage) {
        this.direction = direction;
        this.nextStage = nextStage;
    }

    /**************************************************************************
     * Turns the drone to the relative right or left
     * 
     * @param directionManager the drones direction manager object
     * @param commandCenter the drones command center object
     * @param algorithmManager the programs algorithm manager
    **************************************************************************/
    public String makeDecision(DirectionManager directionManager, CommandCenter commandCenter, AlgorithmManager algorithmManager) {
        Direction currentDirection = directionManager.getDirection();
        algorithmManager.setStage(nextStage);

        if (direction.equals("right")) {
            return commandCenter.makeAction("heading", currentDirection.getRight().toString());
        } else {
            return commandCenter.makeAction("heading", currentDirection.getLeft().toString());
        }
    }
}
