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
}