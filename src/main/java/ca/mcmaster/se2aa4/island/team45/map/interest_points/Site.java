package ca.mcmaster.se2aa4.island.team45.map.interest_points;

import ca.mcmaster.se2aa4.island.team45.map.coordinates.Coordinate;

public class Site {
    Coordinate siteCoordinate;
    String siteID;

    public Site(Coordinate siteCoordinate, String siteID) {
        this.siteCoordinate = siteCoordinate;
        this.siteID = siteID;
    }

    public String getSiteID() {
        return siteID;
    }

    public Coordinate getSiteCoordinate() {
        return siteCoordinate;
    }
}
