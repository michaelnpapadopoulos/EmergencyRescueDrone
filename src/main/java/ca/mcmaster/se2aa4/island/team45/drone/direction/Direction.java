package ca.mcmaster.se2aa4.island.team45.drone.direction;

import ca.mcmaster.se2aa4.island.team45.drone.direction.direction_states.*;

public class Direction {
    private DirectionState directionState;

    /**************************************************************************
     * Direction object constructor
     *
     * @param directionAbbrev the first capital letter of a direction
    **************************************************************************/
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

    /**************************************************************************
     * Checks if this instance of direction is equal to a given instance of
     * direction
     *
     * @param obj a direction obj
    **************************************************************************/
    @Override
    public boolean equals(Object obj) { // Check if direction is equal to obj
        if (obj instanceof Direction) {
            return this.toString().equals(((Direction) obj).toString());
        }
        return false;
    }

    /**************************************************************************
     * Checks if this instance of direction is equal to a string (N,E,S,W)
     *
     * @param droneAbbrev a string (N,E,S,W)
    **************************************************************************/
    public boolean equals(String directionAbbrev) { // Check if direction is equal to directionAbbrev
        return this.toString().equals(directionAbbrev);
    }
    
    /**************************************************************************
     * Gets single character string of direction
    **************************************************************************/
    public String toString() { // Return direction abbreviation (N, E, S, W)
        return directionState.getDirection();
    }

    /**************************************************************************
     * Gets full name of direction
    **************************************************************************/
    public String toFullString() { // Return full direction (North, East, South, West)
        return directionState.getFullDirection();
    }

    /**************************************************************************
     * Gets direction to the left of the current direction
    **************************************************************************/
    public String getLeft() { // Return direction to the left
        return directionState.getLeft();
    }

    /**************************************************************************
     * Gets direction to the right of the current direction
    **************************************************************************/
    public String getRight() { // Return direction to the right
        return directionState.getRight();
    }
}
