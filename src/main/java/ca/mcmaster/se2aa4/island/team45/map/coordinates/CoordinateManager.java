package ca.mcmaster.se2aa4.island.team45.map.coordinates;

import ca.mcmaster.se2aa4.island.team45.drone.commands.CommandCenter;
import ca.mcmaster.se2aa4.island.team45.drone.direction.Direction;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;

public class CoordinateManager {
    private Coordinate currentCoords;
    private CoordinateAdjuster coordAdjuster = new CoordinateAdjuster();

    public CoordinateManager() {
        this.currentCoords = new Coordinate(1, 1); // Always start drone at (1, 1)
    }

    public void adjustCoords(DirectionManager directionManager, CommandCenter commandCenter) {
        this.currentCoords = coordAdjuster.makeAdjustment(directionManager, commandCenter, this.currentCoords);
    }

    public Coordinate getCoordinates() {
        return new Coordinate(getCoordinateX(), getCoordinateY());
    }

    public Coordinate getShiftedCoordinates(Direction direction, int shiftValue) {    
        switch (direction.stringForward()) {
            case "N":
                return new Coordinate(getCoordinateX(), getCoordinateY()-shiftValue);

            case "E":
                return new Coordinate(getCoordinateX()+shiftValue, getCoordinateY());

            case "S":
                return new Coordinate(getCoordinateX(), getCoordinateY()+shiftValue);

            case "W":
                return new Coordinate(getCoordinateX()-shiftValue, getCoordinateY());

                default:
                return null;
        }
    }

    private int getCoordinateX() {
        return currentCoords.getX();
    }

    private int getCoordinateY() {
        return currentCoords.getY();
    }
}