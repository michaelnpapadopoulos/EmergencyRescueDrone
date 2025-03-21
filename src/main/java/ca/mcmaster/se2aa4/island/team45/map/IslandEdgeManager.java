package ca.mcmaster.se2aa4.island.team45.map;

import java.util.HashMap;
import java.util.Map;

public class IslandEdgeManager {
    private Map<String, int[]> edges = new HashMap<>();
    private Map<String, int[]> corners = new HashMap<>();

    public void addMapCorner(String cornerName, int[] cornerCoord) {
        this.corners.put(cornerName, cornerCoord);
    }

    public void addEdge(String direction, int[] coordinates) {
        edges.put(direction, coordinates);
    }

    public int[] getEdge(String direction) {
        return edges.getOrDefault(direction, null);
    }

    public boolean hasEdge(String direction) {
        return edges.containsKey(direction);
    }
}