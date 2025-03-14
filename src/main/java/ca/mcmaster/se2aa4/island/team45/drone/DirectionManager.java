package ca.mcmaster.se2aa4.island.team45.drone;

public class DirectionManager { // Keeps track of the direction the drone is facing
    private char direction;

    public DirectionManager(char direction) {
        this.direction = direction;
    }

    public void changeDirection(char newDirection) {
        this.direction = newDirection;
    }

    public char getDirection() {
        return this.direction;
    }
}
