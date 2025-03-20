package ca.mcmaster.se2aa4.island.team45.map.coordinates;

public class Coordinate {
    private CoordValue[] coordinates = new CoordValue[2];

    public Coordinate(int x, int y) {
        this.coordinates[0] = new CoordValue(x);
        this.coordinates[1] = new CoordValue(y);
    }

    public int getX() {
        return this.coordinates[0].getCoordVal();
    }

    public int getY() {
        return this.coordinates[1].getCoordVal();
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
