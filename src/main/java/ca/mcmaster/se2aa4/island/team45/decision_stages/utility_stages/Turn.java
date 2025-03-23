package ca.mcmaster.se2aa4.island.team45.decision_stages.utility_stages;

import ca.mcmaster.se2aa4.island.team45.decision_stages.Stage;
import ca.mcmaster.se2aa4.island.team45.decision_stages.StageManager;
import ca.mcmaster.se2aa4.island.team45.drone.commands.CommandCenter;
import ca.mcmaster.se2aa4.island.team45.drone.direction.Direction;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;

public class Turn implements Stage { // The initial stage of the mission, find first edge of island
    String direction;
    Stage nextStage;

    public Turn(String direction, Stage nextStage) {
        this.direction = direction;
        this.nextStage = nextStage;
    }

    public String makeDecision(DirectionManager directionManager, CommandCenter commandCenter, StageManager stageManager) {
        Direction currentDirection = directionManager.getDirection();
        stageManager.setStage(nextStage);

        if (direction.equals("right")) {
            return commandCenter.makeAction("heading", currentDirection.stringRight());
        } else {
            return commandCenter.makeAction("heading", currentDirection.stringRight());
        }
    }
}
