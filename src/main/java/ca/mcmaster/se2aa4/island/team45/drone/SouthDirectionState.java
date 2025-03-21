package ca.mcmaster.se2aa4.island.team45.drone;

public class SouthDirectionState implements DirectionState {
    @Override
    public String getLeft() {
        return "E";
    }

    @Override
    public String getRight() {
        return "W";
    }

    @Override
    public String getDirection() {
        return "S";
    }
}
