package ca.mcmaster.se2aa4.island.team45.drone;

public class NorthDirectionState implements DirectionState {
    @Override
    public String getLeft() {
        return "W";
    }

    @Override
    public String getRight() {
        return "E";
    }

    @Override
    public String getDirection() {
        return "N";
    }
}