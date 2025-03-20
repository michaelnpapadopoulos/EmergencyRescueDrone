package ca.mcmaster.se2aa4.island.team45.map.coordinates;

import ca.mcmaster.se2aa4.island.team45.decision_stages.PreviousDecision;
import ca.mcmaster.se2aa4.island.team45.drone.DirectionManager;

public class CoordinateManager {
    private Coordinate currentCoords;
    private CoordinateAdjuster coordAdjuster = new CoordinateAdjuster();


    public CoordinateManager() {
        currentCoords = new Coordinate(1, 1);
    }

    public void adjustCoords(DirectionManager dm, PreviousDecision pd) {
        int[] originalCoords = new int[] {this.coordinates[0].getCoordVal(), this.coordinates[1].getCoordVal()};
        int[] adjustedCoords = coordAdjuster.makeAdjustment(dm, pd, originalCoords);
        this.coordinates[0] = new CoordValue(adjustedCoords[0]);
        this.coordinates[1] = new CoordValue(adjustedCoords[1]);
    }

    public Coordinate getCoordinates() {
        return new Coordinate(getCoordinateX(), getCoordinateY());
    }

    public int getCoordinateX() {
        return this.currentCoords.getX();
    }

    public int getCoordinateY() {
        return this.currentCoords.getY();
    }
}