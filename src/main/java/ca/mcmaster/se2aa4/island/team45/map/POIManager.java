package ca.mcmaster.se2aa4.island.team45.map;

import java.util.ArrayList;
import java.util.HashMap;

public class POIManager {
    private HashMap<String, int[]> islandCorners = new HashMap<String, int[]>();
    private HashMap<String, Integer> islandEdges = new HashMap<String, Integer>();
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

    public void addIslandEdge(String edgeName, int edgeCoord) {
        islandEdges.put(edgeName, edgeCoord);
    }

    public int getIslandEdge(String edgeName) {
        if (islandEdges.containsKey(edgeName)) {
            return islandEdges.get(edgeName);
        }
        return -1; // Edge not in hashmap
    }

}
