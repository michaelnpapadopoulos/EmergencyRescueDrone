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

    public Creek getNearestCreek() {
        //Sets the first creek to the nearest
        Creek nearestCreek = creeks.get(0);
        int nearestX = creeks.get(0).getCreekCoordinate().getX() - emergencySite.getSiteCoordinate().getX();
        int nearestY = creeks.get(0).getCreekCoordinate().getY() - emergencySite.getSiteCoordinate().getY();

        //Checks if each following creek is closer if so sets it to the nreaest and continues until all creeks checked
        for(int i = 1; i < creeks.size(); i++) {
            int currentX = creeks.get(i).getCreekCoordinate().getX() - emergencySite.getSiteCoordinate().getX();
            int currentY = creeks.get(i).getCreekCoordinate().getY() - emergencySite.getSiteCoordinate().getY();

            if (Math.sqrt(Math.pow(currentX,2) + Math.pow(currentY,2)) < Math.sqrt(Math.pow(nearestX,2) + Math.pow(nearestY,2))) {
                nearestCreek = creeks.get(i);
                nearestX = currentX;
                nearestY = currentY;
            }
        }
        return nearestCreek;
    }
}