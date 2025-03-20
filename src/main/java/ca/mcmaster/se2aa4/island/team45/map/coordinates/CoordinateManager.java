package ca.mcmaster.se2aa4.island.team45.map.coordinates;

import ca.mcmaster.se2aa4.island.team45.decision_stages.PreviousDecision;
import ca.mcmaster.se2aa4.island.team45.drone.DirectionManager;

public class CoordinateManager {
    private Coordinate currentCoords;
    private CoordinateAdjuster coordAdjuster = new CoordinateAdjuster();


    public CoordinateManager() {
        currentCoords = new Coordinate(new CoordValue(1), new CoordValue(1));
    }

    public void adjustCoords(DirectionManager dm, PreviousDecision pd) {
        Coordinate originalCoords = this.currentCoords;
        Coordinate adjustedCoords = coordAdjuster.makeAdjustment(dm, pd, originalCoords);
        this.currentCoords = adjustedCoords;
    }

    public Coordinate getCoordinates() {
        return new Coordinate(new CoordValue(getCoordinateX()), new CoordValue(getCoordinateY()));
    }

    public int getCoordinateX() {
        return this.currentCoords.getX().getCoordVal();
    }

    public int getCoordinateY() {
        return this.currentCoords.getY().getCoordVal();
    }
}