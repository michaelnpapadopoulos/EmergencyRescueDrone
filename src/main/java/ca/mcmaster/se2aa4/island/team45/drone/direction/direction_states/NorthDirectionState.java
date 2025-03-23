package ca.mcmaster.se2aa4.island.team45.drone.direction.direction_states;

import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionEnum;

public class NorthDirectionState implements DirectionState {

    @Override
    public String getDirection() {
        return DirectionEnum.NORTH.getShortDir();
    }

    @Override
    public String getLeft() {
        return DirectionEnum.WEST.getShortDir();
    }

    @Override
    public String getRight() {
        return DirectionEnum.EAST.getShortDir();
    }
}