package ca.mcmaster.se2aa4.island.team45.map.coordinates;

public class Coordinate {
    private final int[] coord = new int[2];

    public Coordinate(int x, int y) {
        this.coord[0] = x;
        this.coord[1] = y;
    }

    public int[] printCord() { // WILL REMOVE LATER, HERE FOR DEBUGGING
        return this.coord;
    }

    public int getX() {
        return this.coord[0];
    }

    public int getY() {
        return this.coord[1];
    }
}
