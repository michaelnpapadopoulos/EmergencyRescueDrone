package ca.mcmaster.se2aa4.island.team45.map;

import java.util.HashMap;
import java.util.Map;

public class IslandEdgeManager {
    private Map<String, int[]> edges;

    public IslandEdgeManager() {
        edges = new HashMap<>();
    }

    public void addEdge(String direction, int[] coords) {
        edges.put(direction, coords);
    }

    public int[] getEdge(String direction) {
        return edges.getOrDefault(direction, new int[]{-1, -1});
    }

    public boolean hasEdge(String direction) {
        return edges.containsKey(direction);
    }

    public void calculateAndAddCorners() {
        int[] north = getEdge("North");
        int[] west  = getEdge("West");
        int[] south = getEdge("South");
        int[] east  = getEdge("East");

        addEdge("NorthWest", new int[] {west[0], north[1]});
        addEdge("NorthEast", new int[] {east[0], north[1]});
        addEdge("SouthWest", new int[] {west[0], south[1]});
        addEdge("SouthEast", new int[] {east[0], south[1]});
    }
}