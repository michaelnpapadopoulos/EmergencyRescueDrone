package ca.mcmaster.se2aa4.island.team45;

import ca.mcmaster.se2aa4.island.DirectionManager;
import ca.mcmaster.se2aa4.island.Direction;

public class CoordinateManager {
    private int[] coordinates;
    private DirectionManager dm;

    public CoordinateManager(DirectionManager dm) {
        this.coordinates[0] = 1;
        this.coordinates[1] = 1;
        this.dm = dm;
    }

    public void increment() {
        if (dm.getDirection() == "North") {
            this.coordinates[1] +=1;
        } else if (dm.getDirection() == "East") {
            this.coordinates[0] +=1;
        } else if (dm.getDirection() == "South") {
            this.coordinates[1] -=1;
        } else if (dm.getDirection() == "West") {
            this.coordinates[0] -=1;
        }
    }

    public int[] getCoordinates() {
        return this.coordinates;
    }
}