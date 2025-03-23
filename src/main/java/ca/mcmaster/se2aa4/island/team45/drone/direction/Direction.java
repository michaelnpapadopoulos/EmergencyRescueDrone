package ca.mcmaster.se2aa4.island.team45.drone.direction;

import ca.mcmaster.se2aa4.island.team45.drone.direction.direction_states.DirectionState;
import ca.mcmaster.se2aa4.island.team45.drone.direction.direction_states.EastDirectionState;
import ca.mcmaster.se2aa4.island.team45.drone.direction.direction_states.NorthDirectionState;
import ca.mcmaster.se2aa4.island.team45.drone.direction.direction_states.SouthDirectionState;
import ca.mcmaster.se2aa4.island.team45.drone.direction.direction_states.WestDirectionState;

public class Direction {
    private DirectionState directionState;

    public Direction(DirectionEnum direction) {
        updateDirection(direction);
    }

    public void updateDirection(DirectionEnum direction) {
        switch (direction) {
            case NORTH:
                this.directionState = new NorthDirectionState();
                break;
            case EAST:
                this.directionState = new EastDirectionState();
                break;
            case SOUTH:
                this.directionState = new SouthDirectionState();
                break;
            case WEST:
                this.directionState = new WestDirectionState();
                break;
        }
    }


    public Direction getOppositeDirection() {
        DirectionEnum opposite;
        switch (DirectionEnum.fromString(this.directionState.getDirection())) {
            case NORTH:
                opposite = DirectionEnum.SOUTH;
                break;
            case EAST:
                opposite = DirectionEnum.WEST;
                break;
            case SOUTH:
                opposite = DirectionEnum.NORTH;
                break;
            case WEST:
                opposite = DirectionEnum.EAST;
                break;
            default:
                return null;
        }
        return new Direction(opposite);
    }

    public String getFullDirectionString() {
        return DirectionEnum.fromString(stringForward()).getLongDir();
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
