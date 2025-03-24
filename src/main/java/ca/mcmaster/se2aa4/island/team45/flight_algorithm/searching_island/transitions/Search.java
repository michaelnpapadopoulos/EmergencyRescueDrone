package ca.mcmaster.se2aa4.island.team45.flight_algorithm.searching_island.transitions;

import org.json.JSONArray;

import ca.mcmaster.se2aa4.island.team45.drone.PreviousResult;
import ca.mcmaster.se2aa4.island.team45.drone.direction.*;
import ca.mcmaster.se2aa4.island.team45.map.coordinates.*;
import ca.mcmaster.se2aa4.island.team45.map.interest_points.IslandEdgeManager;

public interface Search {

    /**************************************************************************
     * Checks if the drone is on the edge equivalent to its forward direction
     * 
     * @param islandEdgeManager the programs island edge manager
     * @param directionManager the drones direction manager
     * @param coordinateManager the drones coordinate manager
    **************************************************************************/
    public static boolean atForwardEdge(
        IslandEdgeManager islandEdgeManager, 
        DirectionManager directionManager, 
        CoordinateManager coordinateManager) {
            return islandEdgeManager.atEdge(
                directionManager.getDirection().toString(), 
                coordinateManager.getCoordinates(), 
                directionManager.getDirection().toFullString());
    }

    /**************************************************************************
     * Checks if the drone is at a point from an edge to its right or left by a
     * certain shift value
     * 
     * @param islandEdgeManager the programs island edge manager
     * @param sideEdgeDirection the direction of the edge to check (N,E,S,W)
     * @param coordinateManager the drones coordinate manager
    **************************************************************************/
    public static boolean shiftFromSideEdge(
        IslandEdgeManager islandEdgeManager, 
        Direction sideEdgeDirection, 
        CoordinateManager coordinateManager,
        int shiftValue) {

            Coordinate coordinate = coordinateManager.getCoordinates();
            
            return islandEdgeManager.atEdge(sideEdgeDirection.toString(), 
                    CoordinateUtilities.getShiftedCoordinates(coordinate, sideEdgeDirection, shiftValue), 
                    sideEdgeDirection.toFullString());
    }

    /**************************************************************************
     * Checks if the drone is over water after a scan
     * 
     * @param previousResult the drones previous result
    **************************************************************************/
    public static boolean overWater(
        PreviousResult previousResult) {
            JSONArray biomes = previousResult.getBiomes();
            if (biomes == null) {
                return false;
            }

            boolean waterPresent = false;
            boolean landPresent = false;
            for (int i = 0; i < biomes.length(); i++) {
                if (biomes.getString(i).equals("OCEAN") || biomes.getString(i).equals("LAKE")) {
                    waterPresent = true;
                }

                if (!biomes.getString(i).equals("OCEAN") && !biomes.getString(i).equals("LAKE")) {
                    landPresent = true;
                }
            }

        return waterPresent && !landPresent;
    }
}
