package ca.mcmaster.se2aa4.island.team45.map.coordinates;

import ca.mcmaster.se2aa4.island.team45.drone.commands.CommandCenter;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;

public class CoordinateManager {
    private Coordinate currentCoords;

    public CoordinateManager() {
        this.currentCoords = new Coordinate(1, 1); // Always start drone at (1, 1)
    }

    public Coordinate getCoordinates() { // Returns copy of current coordinates
        return new Coordinate(getCoordinateX(), getCoordinateY());
    }

    public void adjustCoords(DirectionManager directionManager, CommandCenter commandCenter) {
        this.currentCoords = CoordinateUtilities.makeAdjustment(directionManager, commandCenter, this.currentCoords);
    }


    private Integer getCoordinateX() { // Returns int x-coordinate of current coordinates, used for getShiftedCoordinates
        return currentCoords.getX();
    }

    private Integer getCoordinateY() { // Returns int y-coordinate of current coordinates, used for getShiftedCoordinates
        return currentCoords.getY();
    }
}