package ca.mcmaster.se2aa4.island.team45.map.interest_points;

import java.util.ArrayList;

public class POIManager {
    // Points of interest
    private ArrayList<Creek> creeks = new ArrayList<Creek>();
    private Site emergencySite;

    //==== CREEK METHODS ====//
    public void addCreek(Creek newCreek) {
        creeks.add(newCreek);
    }

    public void setSiteLocation(Site newSite) {
        this.emergencySite = newSite;
    }

    // TESTING GETTER
    public ArrayList<Creek> getCreeks() {
        return creeks;
    }

    // TESTING GETTER
    public Site getSite() {
        return emergencySite;
    }
}