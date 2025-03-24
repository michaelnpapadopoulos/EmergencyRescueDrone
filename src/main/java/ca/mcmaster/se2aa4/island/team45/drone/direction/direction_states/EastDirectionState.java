package ca.mcmaster.se2aa4.island.team45.drone.direction.direction_states;

public class EastDirectionState implements DirectionState {

    /**************************************************************************
     * Gets Direction to the left of East
    **************************************************************************/
    @Override
    public String getLeft() {
        return "N";
    }

    /**************************************************************************
     * Gets Direction to the right of East
    **************************************************************************/
    @Override
    public String getRight() {
        return "S";
    }

    /**************************************************************************
     * Gets Direction of East
    **************************************************************************/
    @Override
    public String getDirection() {
        return "E";
    }

    /**************************************************************************
     * Gets full name of East
    **************************************************************************/
    @Override
    public String getFullDirection() {
        return "East";
    }
}