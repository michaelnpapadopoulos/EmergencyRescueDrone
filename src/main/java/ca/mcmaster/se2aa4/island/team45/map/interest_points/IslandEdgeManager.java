package ca.mcmaster.se2aa4.island.team45.map.interest_points;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.map.coordinates.Coordinate;

public class IslandEdgeManager {
    private Map<String, Coordinate> edges = new HashMap<>();
    private List<Coordinate> edgeCoordinates = new ArrayList<>();

    public int numberOfEdgesFound() {
        return edgeCoordinates.size();
    }

    public void addEdge(DirectionManager direction, Coordinate coordinates) {
        Coordinate edge = determineEdge(direction, coordinates);
        edgeCoordinates.add(edge);
    }

    public void labelEdges() {
        if (edgeCoordinates.get(0).getX() == null) {
            if (edgeCoordinates.get(1).getY() > edgeCoordinates.get(0).getY()) {
                edges.put("South", edgeCoordinates.get(1));
                edges.put("North", edgeCoordinates.get(0));
            } else {
                edges.put("North", edgeCoordinates.get(1));
                edges.put("South", edgeCoordinates.get(0));
            }
            if (edgeCoordinates.get(2).getX() > edgeCoordinates.get(3).getX()) {
                edges.put("East", edgeCoordinates.get(2));
                edges.put("West", edgeCoordinates.get(3));
            } else {
                edges.put("West", edgeCoordinates.get(2));
                edges.put("East", edgeCoordinates.get(3));
            }
        } else {
            if (edgeCoordinates.get(1).getX() > edgeCoordinates.get(0).getX()) {
                edges.put("East", edgeCoordinates.get(1));
                edges.put("West", edgeCoordinates.get(0));
            } else {
                edges.put("West", edgeCoordinates.get(1));
                edges.put("East", edgeCoordinates.get(0));
            }
            if (edgeCoordinates.get(2).getY() > edgeCoordinates.get(3).getY()) {
                edges.put("South", edgeCoordinates.get(2));
                edges.put("North", edgeCoordinates.get(3));
            } else {
                edges.put("North", edgeCoordinates.get(2));
                edges.put("South", edgeCoordinates.get(3));
            }
        }
    }

    public Coordinate getEdge(String edgeLabel) {
        if (hasEdge(edgeLabel)) {
            return edges.get(edgeLabel);
        }
        return null;
    }

    public boolean hasEdge(String edgeLabel) { // Takes in "North", "East", "South", or "West"
        return edges.containsKey(edgeLabel);
    }

    private Coordinate determineEdge(DirectionManager directionMan, Coordinate coord) {
        Coordinate edge;
        String currDirection = directionMan.getDirection().toString();
        if (currDirection.equals("N") || currDirection.equals("S")) {
            edge = new Coordinate(null,coord.getY());
            return edge;
        } else if (currDirection.equals("E") || currDirection.equals("W")) {
            edge = new Coordinate(coord.getX(),null); 
            return edge;
        } 
        return null;
    }

    public boolean atEdge(String directionChar, Coordinate currCoord, String edgeLabel) {
        Coordinate edge = getEdge(edgeLabel);
        if (directionChar.equals("N")) {
            if (currCoord.getY() <= edge.getY()) {
                return true;
            }
        } else if (directionChar.equals("S")) {
            if (currCoord.getY() >= edge.getY()) {
                return true;
            }
        } else if (directionChar.equals("E")) {
            if (currCoord.getX() >= edge.getX()) {
                return true;
            }
        } else {
            if (currCoord.getX() <= edge.getX()) {
                return true;
            }
        }

        return false;
    }
}