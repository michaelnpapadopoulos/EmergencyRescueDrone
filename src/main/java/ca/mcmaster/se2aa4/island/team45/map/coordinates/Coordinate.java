package ca.mcmaster.se2aa4.island.team45.map.coordinates;

public class Coordinate {
    private final Integer[] coord = new Integer[2]; // Integer wrapper class used to allow null values

    public Coordinate(Integer x, Integer y) {
        this.coord[0] = x;
        this.coord[1] = y;
    }

    public int[] printCord() { // WILL REMOVE LATER, HERE FOR DEBUGGING
        int[] printCoord = new int[2];

        if (this.coord[0] == null) {
            printCoord[0] = -999;
            printCoord[1] = this.coord[1];

        } else if (this.coord[1] == null) {
            printCoord[0] = this.coord[0];
            printCoord[1] = -999;
            
        } else {
            printCoord[0] = this.coord[0];
            printCoord[1] = this.coord[1];
        } 

        return printCoord;
    }

    public Integer getX() {
        return this.coord[0];
    }

    public Integer getY() {
        return this.coord[1];
    }
}
