package ca.mcmaster.se2aa4.island.team45.drone.direction.direction_states;

public class SouthDirectionState implements DirectionState {

    /**************************************************************************
     * Gets Direction to the left of South
    **************************************************************************/
    @Override
    public String getLeft() {
        return "E";
    }

    /**************************************************************************
     * Gets Direction to the right of South
    **************************************************************************/
    @Override
    public String getRight() {
        return "W";
    }

    /**************************************************************************
     * Gets Direction of South
    **************************************************************************/
    @Override
    public String getDirection() {
        return "S";
    }

    /**************************************************************************
     * Gets full name of South
    **************************************************************************/
    @Override
    public String getFullDirection() {
        return "South";
    }
}