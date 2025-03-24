package ca.mcmaster.se2aa4.island.team45.map.interest_points;

import ca.mcmaster.se2aa4.island.team45.map.coordinates.Coordinate;

public class Site {
    Coordinate siteCoordinate;
    String siteID;

    /**************************************************************************
     * Site constructor
     * 
     * @param siteCoordinate coordinates of the emergency site
     * @param siteID the ID of the emergency site
    **************************************************************************/
    public Site(Coordinate siteCoordinate, String siteID) {
        this.siteCoordinate = siteCoordinate;
        this.siteID = siteID;
    }

    /**************************************************************************
     * Returns the site ID
    **************************************************************************/
    public String getSiteID() {
        return siteID;
    }

    /**************************************************************************
     * Returns the site coordinates
    **************************************************************************/
    public Coordinate getSiteCoordinate() {
        return siteCoordinate;
    }
}
