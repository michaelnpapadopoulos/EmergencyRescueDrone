package ca.mcmaster.se2aa4.island.team45.drone.direction;

public class DirectionManager {
    private Direction direction;

    public DirectionManager(Direction direction) {
        this.direction = direction;
    }

    public void setDirection(String newDirection) {
        this.direction.updateDirection(newDirection);
    }

    public Direction getDirection() {
        return new Direction(this.direction.stringForward());
    }
}