package ca.mcmaster.se2aa4.island.team45.map.interest_points;

import java.util.ArrayList;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.map.coordinates.Coordinate;

public class POIManager {
    // Island information
    private IslandEdgeManager islandEdgeManager;

    // Points of interest
    private ArrayList<Creek> creeks = new ArrayList<Creek>();
    private Site emergencySite;

    public POIManager() {
        this.islandEdgeManager = new IslandEdgeManager();
    }


    //==== CREEK METHODS ====//
    public void addCreek(Creek newCreek) {
        creeks.add(newCreek);
    }

    public void setSiteLocation(Site newSite) {
        this.emergencySite = newSite;
    }

    // TESTING GETTER
    public ArrayList<Creek> getCreeks() {
        return creeks;
    }

    // TESTING GETTER
    public Site getSite() {
        return emergencySite;
    }


    //==== ISLAND EDGE METHODS ====//
    public void addIslandEdge(DirectionManager dm, Coordinate coord) {
        islandEdgeManager.addEdge(dm, coord);
    }

    public Coordinate getIslandEdge(String edgeLabel) {
        return islandEdgeManager.getEdge(edgeLabel);
    }

    public boolean hasIslandEdge(String direction) {
        return islandEdgeManager.hasEdge(direction);
    }

    public int numberOfIslandEdgesFound() {
        return islandEdgeManager.numberOfCoordsFound();
    }

    public void labelIslandEdges() {
        islandEdgeManager.labelEdges();
    }

    public boolean atIslandEdge(String directionChar, Coordinate currentCoord, String edgeLabel) {
        return islandEdgeManager.atEdge(directionChar, currentCoord, edgeLabel);
    }

    
}