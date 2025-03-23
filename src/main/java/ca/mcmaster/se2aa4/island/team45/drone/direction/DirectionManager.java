package ca.mcmaster.se2aa4.island.team45.drone.direction;

public class DirectionManager {
    private Direction direction;

    public DirectionManager(DirectionEnum direction) {
        this.direction = new Direction(direction);
    }

    public void setDirection(String newDirection) {
        this.direction.updateDirection(DirectionEnum.fromString(newDirection));
    }

    public Direction getDirection() {
        return new Direction(DirectionEnum.fromString(this.direction.stringForward()));
    }
}