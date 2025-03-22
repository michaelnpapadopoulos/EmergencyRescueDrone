package ca.mcmaster.se2aa4.island.team45.decision_stages.searching_island;

import ca.mcmaster.se2aa4.island.team45.decision_stages.Stage;
import ca.mcmaster.se2aa4.island.team45.decision_stages.StageManager;
import ca.mcmaster.se2aa4.island.team45.decision_stages.utility_substages.UTurn;
import ca.mcmaster.se2aa4.island.team45.decision_stages.utility_substages.FlyDistance;
import ca.mcmaster.se2aa4.island.team45.drone.battery.BatteryManager;
import ca.mcmaster.se2aa4.island.team45.drone.commands.FlightCommands;
import ca.mcmaster.se2aa4.island.team45.drone.commands.PreviousState;
import ca.mcmaster.se2aa4.island.team45.drone.direction.Direction;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.map.coordinates.CoordinateManager;
import ca.mcmaster.se2aa4.island.team45.map.interest_points.POIManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;

public class SearchFinal implements Stage { // The initial stage of the mission, find first edge of island
    private final Logger logger = LogManager.getLogger();
    private String finalEdgeLabel;
    private Direction finalEdgeDir;

    public SearchFinal(Direction finalEdgeDir) {
        this.finalEdgeDir = finalEdgeDir;
        this.finalEdgeLabel = this.finalEdgeDir.getFullDirectionString(finalEdgeDir.stringForward());
    }

    public String makeDecision(
        DirectionManager direction, 
        BatteryManager battery, 
        PreviousState pState,
        StageManager sm, 
        POIManager poiManager, 
        CoordinateManager cm
        ) {
            logger.info("** Flying towards {} edge **", finalEdgeLabel);

            //====== LOGIC FOR CHECKING IF DRONES AT AN ISLAND EDGE ======//
            // If drone is at the edge of the island, make a U-turn
            if (poiManager.atIslandEdge(direction.getDirection().stringForward(), cm.getCoordinates(), direction.getDirection().getFullDirectionString(direction.getDirection().stringForward()))) {
                logger.info("** Reaching {} edge **", direction.getDirection().getFullDirectionString(direction.getDirection().stringForward()));

                // If drone is at the edge of the island and the perpendicular edge to the drone is the final edge, turn on the spot and transition to SearchFinal
                if (poiManager.atIslandEdge(finalEdgeDir.stringForward(), cm.getOffsetCoordinates(finalEdgeDir, 1), this.finalEdgeLabel)) {
                    pState.setPrevAction("stop");
                    return FlightCommands.getInstance().stop();
                }

                // If drone is at the edge of the island and the edge is not the final edge, do a normal U-Turn and continue searching
                pState.setPrevAction("fly");
                sm.setStage(new UTurn(pState.getOppositeUTurn(), new SearchFinal(finalEdgeDir)));
                return FlightCommands.getInstance().fly();
            }

            //====== LOGIC FOR FLYING OVER WATER ======//
            if (pState.getPrevAction().equals("scan") && overWater(pState)) {

                logger.info("** Echoing over water **");
                sm.setStage(new FlyDistance(new SearchFinal(finalEdgeDir)));

                pState.setPrevAction("echo");
                pState.setPrevHeading(direction.getDirection().stringForward());
                return FlightCommands.getInstance().echo(direction.getDirection().stringForward());
            } 

            //====== LOGIC FOR FLYING FORWARD AND SCANNING ======//
            if (pState.getPrevAction().equals("fly")) {
                pState.setPrevAction("scan");
                return FlightCommands.getInstance().scan();
            } else {
                // if (pState.retrieveCreeks() != null) {
                //     for (int i = 0; i < pState.retrieveCreeks().length; i++) {
                //         poiManager.addCreek(new Creek(cm.getCoordinates(), pState.retrieveCreeks()[i]));
                //     }
                // }

                // if (pState.retrieveSites() != null) {
                //     for (int i = 0; i < pState.retrieveSites().length; i++) {
                //         poiManager.setSiteLocation(new Site(cm.getCoordinates(), pState.retrieveSites()[i]));
                //     }
                // }
                
                pState.setPrevAction("fly");
                return FlightCommands.getInstance().fly();
            }
    }

    //===== HELPER METHOD TO CHECK IF DRONES OVER WATER =====//
    private boolean overWater(PreviousState previousState) {
        logger.info("** Checking if drone is over water **");
        JSONArray biomes = previousState.getBiomes();
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

        if (waterPresent && !landPresent) {
            return true;
        }

        return false;
    }
}