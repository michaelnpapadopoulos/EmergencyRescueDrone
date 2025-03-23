package ca.mcmaster.se2aa4.island.team45.map.interest_points;

import ca.mcmaster.se2aa4.island.team45.map.coordinates.Coordinate;

public class Creek {
    Coordinate creekCoordinate;
    String creekID;

    public Creek(Coordinate creekCoordinate, String creekID) {
        this.creekCoordinate = creekCoordinate;
        this.creekID = creekID;
    }

    public String getCreekID() {
        return creekID;
    }

    public Coordinate getCreekCoordinate() {
        return creekCoordinate;
    }
}

