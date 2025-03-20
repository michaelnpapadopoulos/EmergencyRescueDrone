package ca.mcmaster.se2aa4.island.team45.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.mcmaster.se2aa4.island.team45.drone.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.map.coordinates.CoordValue;

public class IslandEdgeManager {
    private Map<String, CoordValue[]> edges = new HashMap<>();
    private List<CoordValue[]> edgeCoordinates = new ArrayList<>();

    public void addEdge(DirectionManager direction, int[] coordinates) {
        CoordValue[] edge = new CoordValue[2];
        edge = determineEdge(direction, coordinates);
        edgeCoordinates.add(edge);
        
    }

    // public int[] getEdge(String direction) {
    //     if (!edges.containsKey(direction)) {
    //         return null;
    //     }
    //     return edges.get(direction);
    // }

    public boolean hasEdge(String direction) {
        return edges.containsKey(direction);
    }

    private int[] determineEdge(DirectionManager direction, int[] coord) {
        CoordValue[] edge = new int[2];
        String currDirection = direction.getDirection();
        if (currDirection.equals("N") || currDirection.equals("S")) {
            edge[0] = coord[0];
            edge[1] = 0;
        } else if (currDirection.equals("E")) {
            edge[0] = 0;
            edge[1] = coord[1];
        } 
            
        }
        return edge;
    }
}