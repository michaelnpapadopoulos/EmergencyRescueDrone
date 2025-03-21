package ca.mcmaster.se2aa4.island.team45.drone.direction;

import ca.mcmaster.se2aa4.island.team45.drone.direction.direction_states.DirectionState;
import ca.mcmaster.se2aa4.island.team45.drone.direction.direction_states.EastDirectionState;
import ca.mcmaster.se2aa4.island.team45.drone.direction.direction_states.NorthDirectionState;
import ca.mcmaster.se2aa4.island.team45.drone.direction.direction_states.SouthDirectionState;
import ca.mcmaster.se2aa4.island.team45.drone.direction.direction_states.WestDirectionState;

public class Direction {
    private DirectionState directionState;

    public Direction(String direction) {
        updateDirection(direction);
    }

    public void updateDirection(String direction) {
        switch (direction) {
            case "N":
                this.directionState = new NorthDirectionState();
                break;
            case "E":
                this.directionState = new EastDirectionState();
                break;
            case "S":
                this.directionState = new SouthDirectionState();
                break;
            case "W":
                this.directionState = new WestDirectionState();
                break;
        }
    }

    public String stringForward() {
        return directionState.getDirection();
    }

    public String stringLeft() {
        return directionState.getLeft();
    }

    public String stringRight() {
        return directionState.getRight();
    }
}
