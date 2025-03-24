package ca.mcmaster.se2aa4.island.team45.drone.direction.direction_states;

public class NorthDirectionState implements DirectionState {
    @Override

    /**************************************************************************
     * Gets Direction to the left of North
    **************************************************************************/
    public String getLeft() {
        return "W";
    }

    /**************************************************************************
     * Gets Direction to the right of North
    **************************************************************************/
    @Override
    public String getRight() {
        return "E";
    }

    /**************************************************************************
     * Gets Direction of North
    **************************************************************************/
    @Override
    public String getDirection() {
        return "N";
    }

    /**************************************************************************
     * Gets full name of North
    **************************************************************************/
    @Override
    public String getFullDirection() {
        return "North";
    }
}