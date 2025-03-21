package ca.mcmaster.se2aa4.island.team45.map.coordinates;

public class Coordinate {
    private CoordValue[] coordinates = new CoordValue[2];

    public Coordinate(CoordValue x, CoordValue y) {
        this.coordinates[0] = x;
        this.coordinates[1] = y;
    }

    public int[] printCord() { // WILL REMOVE LATER, HERE FOR DEBUGGING
        int[] cord = new int[2];
        if (this.coordinates[0] == null) {
            cord[0] = 69;
            cord[1] = this.coordinates[1].getCoordVal();
        } else if (this.coordinates[1] == null) {
            cord[1] = 420;
            cord[0] = this.coordinates[0].getCoordVal();
        } else {
            cord[0] = this.coordinates[0].getCoordVal();
            cord[1] = this.coordinates[1].getCoordVal();
        }

        return cord;
    }

    public CoordValue getX() {
        return this.coordinates[0];
    }

    public CoordValue getY() {
        return this.coordinates[1];
    }

    public void incrementX() {
        this.coordinates[0].increment(); 
    }

    public void decrementX() {
        this.coordinates[0].decrement();
    }

    public void incrementY() {
        this.coordinates[1].increment();
    }

    public void decrementY() {
        this.coordinates[1].decrement();
    }
}
