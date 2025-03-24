package ca.mcmaster.se2aa4.island.team45.map.coordinates;

public class Coordinate {
    private final Integer[] coord = new Integer[2]; // Integer wrapper class used to allow null values

    /**************************************************************************
     * Coordinate constructor
     * 
     * @param x the x coordinate
     * @param y the y coordinate
    **************************************************************************/
    public Coordinate(Integer x, Integer y) {
        this.coord[0] = x;
        this.coord[1] = y;
    }

    /**************************************************************************
     * Prints the coordinate, if a coordinate value is null it prints -999
    **************************************************************************/
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

    /**************************************************************************
     * Returns the X coordinate
    **************************************************************************/
    public Integer getX() {
        return this.coord[0];
    }

    /**************************************************************************
     * Returns the Y coordinate
    **************************************************************************/
    public Integer getY() {
        return this.coord[1];
    }
}
