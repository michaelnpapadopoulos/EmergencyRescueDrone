package ca.mcmaster.se2aa4.island.team45.map.interest_points;

import ca.mcmaster.se2aa4.island.team45.map.coordinates.Coordinate;

public class Creek {
    private Coordinate creekCoordinate;
    private String creekID;

    /**************************************************************************
     * Creek constructor
     * 
     * @param creekCoordinate the drones coordinates when creek is found
     * @param creekID the creeks ID found from the scanned JSONObject
    **************************************************************************/
    public Creek(Coordinate creekCoordinate, String creekID) {
        this.creekCoordinate = creekCoordinate;
        this.creekID = creekID;
    }

    /**************************************************************************
     * Returns the creek instance's ID
    **************************************************************************/
    public String getCreekID() { return creekID; }

    /**************************************************************************
     * Returns the creek instance's coordinate
    **************************************************************************/
    public Coordinate getCreekCoordinate() { return creekCoordinate; }
}

