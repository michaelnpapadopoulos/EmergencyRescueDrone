package ca.mcmaster.se2aa4.island.team45.drone.direction.direction_states;

import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionEnum;

public class SouthDirectionState implements DirectionState {

    @Override
    public String getDirection() {
        return DirectionEnum.SOUTH.getShortDir();
    }

    @Override
    public String getLeft() {
        return DirectionEnum.EAST.getShortDir();
    }

    @Override
    public String getRight() {
        return DirectionEnum.WEST.getShortDir();
    }
}