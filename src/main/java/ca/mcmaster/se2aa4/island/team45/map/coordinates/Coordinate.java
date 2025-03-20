package ca.mcmaster.se2aa4.island.team45.map.coordinates;

public class Coordinate {
    private CoordValue[] coordinates = new CoordValue[2];

    public Coordinate(CoordValue x, CoordValue y) {
        this.coordinates[0] = x;
        this.coordinates[1] = y;
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
