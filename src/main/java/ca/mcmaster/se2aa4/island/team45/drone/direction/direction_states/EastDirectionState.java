package ca.mcmaster.se2aa4.island.team45.drone.direction.direction_states;

public class EastDirectionState implements DirectionState {
    @Override
    public String getLeft() {
        return "N";
    }

    @Override
    public String getRight() {
        return "S";
    }

    @Override
    public String getDirection() {
        return "E";
    }

    @Override
    public String getFullDirection() {
        return "East";
    }
}