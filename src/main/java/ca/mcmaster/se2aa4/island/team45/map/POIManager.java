package ca.mcmaster.se2aa4.island.team45.map;

import java.util.ArrayList;
import java.util.HashMap;

public class POIManager {
    // Island information
    private HashMap<String, int[]> islandCorners = new HashMap<String, int[]>();
    private IslandEdgeManager islandEdgeManager;

    // Points of interest
    private ArrayList<int[]> creeks = new ArrayList<int[]>();
    private int[] siteLocation = new int[2];

    public POIManager() {
        this.islandEdgeManager = new IslandEdgeManager();
    }

    public void addCreek(int[] creekCoord) {
        creeks.add(creekCoord);
    }

    public void addMapCorner(String cornerName, int[] cornerCoord) {
        this.islandCorners.put(cornerName, cornerCoord);
    }

    public void setSiteLocation(int[] siteCoord) {
        siteLocation = siteCoord;
    }

    public void addIslandEdge(String direction, int[] coordinates) {
        islandEdgeManager.addEdge(direction, coordinates);
    }

    public int[] getIslandEdge(String direction) {
        return islandEdgeManager.getEdge(direction);
    }

    public boolean hasIslandEdge(String direction) {
        return islandEdgeManager.hasEdge(direction);
    }
}
