package ca.mcmaster.se2aa4.island.team45.map;

import java.util.ArrayList;
import java.util.HashMap;

public class POIManager {
    // Island information
    private HashMap<String, int[]> islandCorners = new HashMap<String, int[]>();
    private HashMap<String, int[]> islandEdges = new HashMap<String, int[]>(); // The int[] array will store a single value for x or y, and then a -1 to indicate the other value is not set

    // Site and creek information
    private ArrayList<int[]> creeks = new ArrayList<int[]>();
    private int[] siteLocation = new int[2];
    
    public void addCreek(int[] creekCoord) {
        creeks.add(creekCoord);
    }

    public void addMapCorner(String cornerName, int[] cornerCoord) {
        this.islandCorners.put(cornerName, cornerCoord);
    }

    public void setSiteLocation(int[] siteCoord) {
        siteLocation = siteCoord;
    }

    public void addIslandEdge(String edgeName, int[] edgeCoord) {
        islandEdges.put(edgeName, edgeCoord);
    }

    public int[] getIslandEdge(String edgeName) {
        if (islandEdges.containsKey(edgeName)) {
            return islandEdges.get(edgeName);
        }
        return null; // Edge not in hashmap
    }

}
