package ca.mcmaster.se2aa4.island.team45.map.interest_points;

import java.util.ArrayList;

public class POIManager {
    private ArrayList<Creek> creeks = new ArrayList<Creek>();
    private Site emergencySite;

    /**************************************************************************
     * Adds a creek to the list of creeks
     * 
     * @param newCreek a creek object representing a creek we found
    **************************************************************************/
    public void addCreek(Creek newCreek) { creeks.add(newCreek); }

    /**************************************************************************
     * Adds the sites location
     * 
     * @param newSite a site object representing the emergency site
    **************************************************************************/
    public void setSiteLocation(Site newSite) { this.emergencySite = newSite; }

    /**************************************************************************
     * Returns the list of creeks
    **************************************************************************/
    public ArrayList<Creek> getCreeks() { return creeks; }

    /**************************************************************************
     * Returns the emergency site
    **************************************************************************/
    public Site getSite() { return emergencySite; }

    /**************************************************************************
     * Checks if we found a creek
    **************************************************************************/
    public boolean hasCreeks() { return !creeks.isEmpty(); }

    /**************************************************************************
     * Checks if we found an emergency site
    **************************************************************************/
    public boolean hasSite() { return emergencySite != null; }

    /**************************************************************************
     * Finds how to format the report based off what POI's we have found
    **************************************************************************/
    public int reportCase() {
        if(hasCreeks() && hasSite()) {
            return 1;
        } else if (hasSite()) {
            return 2;
        } else if (hasCreeks()){
            return 3;
        } else {
            return 4;
        }
    }

    /**************************************************************************
     * Uses Pythagorean theorem to find the nearest creek to the emergency siteS
    **************************************************************************/
    public Creek getNearestCreek() {
        if (!hasCreeks()) {
            return null;
        }
        
        if (!hasSite()) {
            // Cannot calculate nearest creek without an emergency site
            return null;
        }
        
        // Sets the first creek to the nearest
        Creek nearestCreek = creeks.getFirst();
        int nearestX = Math.abs((int)creeks.getFirst().getCreekCoordinate().getX() - (int)emergencySite.getSiteCoordinate().getX());
        int nearestY = Math.abs((int)creeks.getFirst().getCreekCoordinate().getY() - (int)emergencySite.getSiteCoordinate().getY());

        // Checks if each following creek is closer if so sets it to the nearest and continues until all creeks checked
        for(int i = 1; i < creeks.size(); i++) {
            int currentX = Math.abs((int)creeks.get(i).getCreekCoordinate().getX() - (int)emergencySite.getSiteCoordinate().getX());
            int currentY = Math.abs((int)creeks.get(i).getCreekCoordinate().getY() - (int)emergencySite.getSiteCoordinate().getY());

            if (Math.sqrt(Math.pow(currentX,2) + Math.pow(currentY,2)) < Math.sqrt(Math.pow(nearestX,2) + Math.pow(nearestY,2))) {
                nearestCreek = creeks.get(i);
                nearestX = currentX;
                nearestY = currentY;
            }
        }
        
        return nearestCreek;
    }
}