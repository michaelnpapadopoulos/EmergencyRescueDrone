package ca.mcmaster.se2aa4.island.team45.drone;

public class WestDirectionState implements DirectionState {
    @Override
    public String getLeft() {
        return "S";
    }

    @Override
    public String getRight() {
        return "N";
    }

    @Override
    public String getDirection() {
        return "W";
    }
}
