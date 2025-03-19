package ca.mcmaster.se2aa4.island.team45.map;

import ca.mcmaster.se2aa4.island.team45.decision_stages.PreviousDecision;
import ca.mcmaster.se2aa4.island.team45.drone.DirectionManager;

public class CoordinateManager {
    private int[] coordinates = new int[2];
    private CoordinateAdjuster coordAdjuster = new CoordinateAdjuster();


    public CoordinateManager() {
        this.coordinates[0] = 1;
        this.coordinates[1] = 1;
    }


    public void adjustCoords(DirectionManager dm, PreviousDecision pd) {
        this.coordinates = coordAdjuster.makeAdjustment(dm, pd, this.coordinates);
    }

    public int[] getCoordinates() {
        return this.coordinates;
    }

    public int getX() {
        return this.coordinates[0];
    }

    public int getY() {
        return this.coordinates[1];
    }
}