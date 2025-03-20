package ca.mcmaster.se2aa4.island.team45.map.coordinates;

public class CoordValue {
    int coord;

    public CoordValue(int coord) {
        this.coord = coord;
    }

    public int getCoordVal() {
        return coord;
    }

    public void increment() {
        coord++;
    }

    public void decrement() {
        coord--;
    }
}
