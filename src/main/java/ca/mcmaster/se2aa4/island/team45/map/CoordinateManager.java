package ca.mcmaster.se2aa4.island.team45.map;

public class CoordinateManager {
    private int[] coordinates;

    public CoordinateManager() {
        this.coordinates[0] = 1;
        this.coordinates[1] = 1;
    }

    public void incrementNorth(String newDirection) {
        this.coordinates[1] +=1;
    }

    public void incrementEast(String newDirection) {
        this.coordinates[0] +=1;
    }

    public void incrementSouth(String newDirection) {
        this.coordinates[1] -=1;
    }

    public void incrementWest(String newDirection) {
        this.coordinates[0] -=1;
    }

    public int[] getCoordinates() {
        return this.coordinates;
    }
}