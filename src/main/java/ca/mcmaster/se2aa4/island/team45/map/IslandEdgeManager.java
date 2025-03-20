package ca.mcmaster.se2aa4.island.team45.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.mcmaster.se2aa4.island.team45.map.coordinates.Coordinate;
import ca.mcmaster.se2aa4.island.team45.drone.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.map.coordinates.CoordValue;

public class IslandEdgeManager {
    private Map<String, Coordinate> edges = new HashMap<>();
    private List<Coordinate> edgeCoordinates = new ArrayList<>();

    public void addEdge(DirectionManager direction, Coordinate coordinates) {
        Coordinate edge = determineEdge(direction, coordinates);
        edgeCoordinates.add(edge);
    }

    public void determineEdges() {
        if (edgeCoordinates.get(0).getX() == null) {
            if (edgeCoordinates.get(1).getY().getCoordVal() > edgeCoordinates.get(0).getY().getCoordVal()) {
                edges.put("South", edgeCoordinates.get(1));
                edges.put("North", edgeCoordinates.get(0));
            } else {
                edges.put("North", edgeCoordinates.get(1));
                edges.put("South", edgeCoordinates.get(0));
            }
            if (edgeCoordinates.get(2).getX().getCoordVal() > edgeCoordinates.get(3).getX().getCoordVal()) {
                edges.put("East", edgeCoordinates.get(2));
                edges.put("West", edgeCoordinates.get(3));
            } else {
                edges.put("West", edgeCoordinates.get(2));
                edges.put("East", edgeCoordinates.get(3));
            }
        } else {
            if (edgeCoordinates.get(1).getX().getCoordVal() > edgeCoordinates.get(0).getX().getCoordVal()) {
                edges.put("East", edgeCoordinates.get(1));
                edges.put("West", edgeCoordinates.get(0));
            } else {
                edges.put("West", edgeCoordinates.get(1));
                edges.put("East", edgeCoordinates.get(0));
            }
            if (edgeCoordinates.get(2).getY().getCoordVal() > edgeCoordinates.get(3).getY().getCoordVal()) {
                edges.put("South", edgeCoordinates.get(2));
                edges.put("North", edgeCoordinates.get(3));
            } else {
                edges.put("North", edgeCoordinates.get(2));
                edges.put("South", edgeCoordinates.get(3));
            }
        }
        
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

    private Coordinate determineEdge(DirectionManager direction, Coordinate coord) {
        Coordinate edge;
        String currDirection = direction.getDirection();
        if (currDirection.equals("N") || currDirection.equals("S")) {
            edge = new Coordinate(null,coord.getY());
            return edge;
        } else if (currDirection.equals("E") || currDirection.equals("W")) {
            edge = new Coordinate(coord.getX(),null); 
            return edge;
        } 
        return null;
    }
}