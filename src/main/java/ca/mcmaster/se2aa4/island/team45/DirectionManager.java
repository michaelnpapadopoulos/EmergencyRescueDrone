package ca.mcmaster.se2aa4.island.team45;

import ca.mcmaster.se2aa4.island.Direction;

public class DirectionManager {
    private Direction direction;

    public DirectionManager(Direction direction) {
        this.direction = direction;
    }

    public void changeDirection(Direction newDirection) {
        this.direction = newDirection;
    }

    public Direction getDirection() {
        return this.direction;
    }
}
