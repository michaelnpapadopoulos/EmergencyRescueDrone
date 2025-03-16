package ca.mcmaster.se2aa4.island.team45.map;

import java.util.ArrayList;
import java.util.HashMap;

public class POIManager {
    private HashMap<String, int[]> mapCorners = new HashMap<String, int[]>();
    private ArrayList<int[]> creeks = new ArrayList<int[]>();
    private int[] siteLocation = new int[2];
    

    public POIManager() {}

    public void addCreek(int[] creekCoord) {
        creeks.add(creekCoord);
    }

    public void addMapCorner(String cornerName, int[] cornerCoord) {
        mapCorners.put(cornerName, cornerCoord);
    }

    public void setSiteLocation(int[] siteCoord) {
        siteLocation = siteCoord;
    }
}
