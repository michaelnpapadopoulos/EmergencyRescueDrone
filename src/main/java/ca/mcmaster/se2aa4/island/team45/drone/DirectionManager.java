package ca.mcmaster.se2aa4.island.team45.drone;

public class DirectionManager {
    private String direction;

    public DirectionManager(String direction) {
        this.direction = direction;
    }

    public void changeDirection(String newDirection) {
        this.direction = newDirection;
    }

    public String getDirection() {
        return this.direction;
    }
}
