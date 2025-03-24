package ca.mcmaster.se2aa4.island.team45.drone.direction;

public class DirectionManager {
    private Direction direction;

    public DirectionManager(Direction droneDirection) {
        this.direction = droneDirection;
    }

    public void setDirection(String newDirection) {
        this.direction = new Direction(newDirection);
    }

    public Direction getDirection() { // Returns copy of current direction
        return new Direction(this.direction.toString());
    }
}