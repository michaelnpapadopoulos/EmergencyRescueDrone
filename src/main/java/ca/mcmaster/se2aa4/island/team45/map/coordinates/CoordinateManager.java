package ca.mcmaster.se2aa4.island.team45.map.coordinates;

import ca.mcmaster.se2aa4.island.team45.drone.commands.CommandCenter;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;

public class CoordinateManager {
    private Coordinate currentCoords;

    /**************************************************************************
     * Coordinate manager constructor
    **************************************************************************/
    public CoordinateManager() {
        this.currentCoords = new Coordinate(1, 1); // Always start drone at (1, 1)
    }

    /**************************************************************************
     * Returns your current coordinates
    **************************************************************************/
    public Coordinate getCoordinates() { // Returns copy of current coordinates
        return new Coordinate(getCoordinateX(), getCoordinateY());
    }

    /**************************************************************************
     * Updates your drones current coords depending on your previous action
     * 
     * @param directionManager the drones direction manager
     * @param commandCenter the drones command center
    **************************************************************************/
    public void adjustCoords(DirectionManager directionManager, CommandCenter commandCenter) {
        this.currentCoords = CoordinateUtilities.makeAdjustment(directionManager, commandCenter, this.currentCoords);
    }

    /**************************************************************************
     * Gets the drones current X coordinate
    **************************************************************************/
    private Integer getCoordinateX() { // Returns int x-coordinate of current coordinates, used for getShiftedCoordinates
        return currentCoords.getX();
    }

    /**************************************************************************
     * Gets the drones current Y coordinate
    **************************************************************************/
    private Integer getCoordinateY() { // Returns int y-coordinate of current coordinates, used for getShiftedCoordinates
        return currentCoords.getY();
    }
}