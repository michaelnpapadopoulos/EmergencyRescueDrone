package ca.mcmaster.se2aa4.island.team45.map.interest_points;

import java.util.ArrayList;

public class POIManager {
    private ArrayList<Creek> creeks = new ArrayList<Creek>();
    private Site emergencySite;

    public void addCreek(Creek newCreek) {
        creeks.add(newCreek);
    }

    public void setSiteLocation(Site newSite) {
        this.emergencySite = newSite;
    }

    public ArrayList<Creek> getCreeks() {
        return creeks;
    }

    public Site getSite() {
        return emergencySite;
    }

    public boolean hasCreeks() {
        return !creeks.isEmpty();
    }

    public boolean hasSite() {
        return emergencySite != null;
    }

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

    public Creek getNearestCreek() {
        if (!hasCreeks()) {
            return null;
        }
        
        if (!hasSite()) {
            // Cannot calculate nearest creek without an emergency site
            return null;
        }
        
        // Sets the first creek to the nearest
        Creek nearestCreek = creeks.get(0);
        int nearestX = Math.abs((int)creeks.get(0).getCreekCoordinate().getX() - (int)emergencySite.getSiteCoordinate().getX());
        int nearestY = Math.abs((int)creeks.get(0).getCreekCoordinate().getY() - (int)emergencySite.getSiteCoordinate().getY());

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