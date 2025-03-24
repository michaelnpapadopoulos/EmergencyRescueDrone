package ca.mcmaster.se2aa4.island.team45.drone.direction.direction_states;

public class WestDirectionState implements DirectionState {

    /**************************************************************************
     * Gets Direction to the left of West
    **************************************************************************/
    @Override
    public String getLeft() {
        return "S";
    }

    /**************************************************************************
     * Gets Direction to the right of West
    **************************************************************************/
    @Override
    public String getRight() {
        return "N";
    }

    /**************************************************************************
     * Gets Direction of West
    **************************************************************************/
    @Override
    public String getDirection() {
        return "W";
    }

    /**************************************************************************
     * Gets full name of West
    **************************************************************************/
    @Override
    public String getFullDirection() {
        return "West";
    }
}