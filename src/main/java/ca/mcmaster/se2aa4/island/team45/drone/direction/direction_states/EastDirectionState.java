package ca.mcmaster.se2aa4.island.team45.drone.direction.direction_states;

import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionEnum;

public class EastDirectionState implements DirectionState {

    @Override
    public String getDirection() {
        return DirectionEnum.EAST.getShortDir();
    }

    @Override
    public String getLeft() {
        return DirectionEnum.NORTH.getShortDir();
    }

    @Override
    public String getRight() {
        return DirectionEnum.SOUTH.getShortDir();
    }
}