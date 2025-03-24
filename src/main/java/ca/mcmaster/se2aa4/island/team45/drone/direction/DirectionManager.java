package ca.mcmaster.se2aa4.island.team45.drone.direction;

public class DirectionManager {
    private Direction direction;

    /**************************************************************************
     * Direction manager constructor
     *
     * @param droneDirection initial direction for the direction manager
    **************************************************************************/
    public DirectionManager(Direction droneDirection) {
        this.direction = droneDirection;
    }

    /**************************************************************************
     * Sets the drones direction to a new direction
     *
     * @param newDirection new direction of the drone
    **************************************************************************/
    public void setDirection(String newDirection) {
        this.direction = new Direction(newDirection);
    }

    /**************************************************************************
     * Returns a copy of the drones current direction
    **************************************************************************/
    public Direction getDirection() { // Returns copy of current direction
        return new Direction(this.direction.toString());
    }
}