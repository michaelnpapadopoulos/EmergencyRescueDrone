package ca.mcmaster.se2aa4.island.team45.map;

import java.util.ArrayList;
import java.util.HashMap;

import ca.mcmaster.se2aa4.island.team45.drone.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.map.coordinates.CoordValue;
import ca.mcmaster.se2aa4.island.team45.map.coordinates.Coordinate;

public class POIManager {
    // Island information
    private IslandEdgeManager islandEdgeManager;

    // Points of interest
    private ArrayList<Coordinate> creeks = new ArrayList<Coordinate>();
    private Coordinate siteLocation;

    public POIManager() {
        this.islandEdgeManager = new IslandEdgeManager();
    }

    public void addCreek(Coordinate creekCoord) {
        creeks.add(creekCoord);
    }

    public void setSiteLocation(Coordinate siteCoord) {
        siteLocation = siteCoord;
    }

    public void addIslandEdge(DirectionManager dm, Coordinate coord) {
        islandEdgeManager.addEdge(dm, coord);
    }

    public int[] getIslandEdge(String direction) {
        return islandEdgeManager.getEdge(direction);
    }

    public boolean hasIslandEdge(String direction) {
        return islandEdgeManager.hasEdge(direction);
    }
}
