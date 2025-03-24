package ca.mcmaster.se2aa4.island.team45.drone.direction;

import ca.mcmaster.se2aa4.island.team45.drone.direction.direction_states.*;

public class Direction {
    private DirectionState directionState;

    public Direction(String directionAbbrev) {
        switch (directionAbbrev) {
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

    @Override
    public boolean equals(Object obj) { // Check if direction is equal to obj
        if (obj instanceof Direction) {
            return this.toString().equals(((Direction) obj).toString());
        }
        return false;
    }

    public boolean equals(String directionAbbrev) { // Check if direction is equal to directionAbbrev
        return this.toString().equals(directionAbbrev);
    }
 
    public String toString() { // Return direction abbreviation (N, E, S, W)
        return directionState.getDirection();
    }

    public String toFullString() { // Return full direction (North, East, South, West)
        return directionState.getFullDirection();
    }

    public String getLeft() { // Return direction to the left
        return directionState.getLeft();
    }

    public String getRight() { // Return direction to the right
        return directionState.getRight();
    }
}
