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
            case "North":
                this.directionState = new NorthDirectionState();
                break;
            case "E":
                this.directionState = new EastDirectionState();
                break;
            case "East":
                this.directionState = new EastDirectionState();
                break;
            case "S":
                this.directionState = new SouthDirectionState();
                break;
            case "South":
                this.directionState = new SouthDirectionState();
                break;
            case "W":
                this.directionState = new WestDirectionState();
                break;
            case "West":
                this.directionState = new WestDirectionState();
                break;
        }
    }

    public Direction getOppositeDirection() {
        Direction oppDirection;
        switch (this.directionState.getDirection()) {
            case "N":
                oppDirection = new Direction("S");
                return oppDirection;
            case "E":
                oppDirection = new Direction("W");
                return oppDirection;
            case "S":
                oppDirection = new Direction("N");
                return oppDirection;
            case "W":
                oppDirection = new Direction("E");
                return oppDirection;
        }
        return null;
    }

    public String getFullDirectionString() {
        switch (stringForward()) {
            case "N":
                return "North";
            case "E":
                return "East";
            case "S":
                return "South";
            case "W":
                return "West";
            default:
                return "Invalid direction";
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
