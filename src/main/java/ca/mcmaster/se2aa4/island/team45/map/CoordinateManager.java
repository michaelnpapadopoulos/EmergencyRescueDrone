package ca.mcmaster.se2aa4.island.team45.map;

import ca.mcmaster.se2aa4.island.team45.decision_stages.PreviousDecision;
import ca.mcmaster.se2aa4.island.team45.drone.DirectionManager;

public class CoordinateManager {
    private CoordValue[] coordinates = new CoordValue[2];
    private CoordinateAdjuster coordAdjuster = new CoordinateAdjuster();


    public CoordinateManager() {
        this.coordinates[0] = new CoordValue(1);
        this.coordinates[1] = new CoordValue(1);
    }


    public void adjustCoords(DirectionManager dm, PreviousDecision pd) {
        int[] originalCoords = new int[] {this.coordinates[0].getCoordVal(), this.coordinates[1].getCoordVal()};
        int[] adjustedCoords = coordAdjuster.makeAdjustment(dm, pd, originalCoords);
        this.coordinates[0] = new CoordValue(adjustedCoords[0]);
        this.coordinates[1] = new CoordValue(adjustedCoords[1]);
    }

    public int[] getCoordinates() {
        return new int[] {this.coordinates[0].getCoordVal(), this.coordinates[1].getCoordVal()};
    }

    public int getX() {
        return this.coordinates[0].getCoordVal();
    }

    public int getY() {
        return this.coordinates[1].getCoordVal();
    }
}