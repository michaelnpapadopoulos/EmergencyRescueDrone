package ca.mcmaster.se2aa4.island.team45.flight_algorithm.utility_stages;

import ca.mcmaster.se2aa4.island.team45.drone.commands.CommandCenter;
import ca.mcmaster.se2aa4.island.team45.drone.direction.*;
import ca.mcmaster.se2aa4.island.team45.flight_algorithm.*;

public class Turn implements Stage { // The initial stage of the mission, find first edge of island
    String direction;
    Stage nextStage;

    public Turn(String direction, Stage nextStage) {
        this.direction = direction;
        this.nextStage = nextStage;
    }

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
