package ca.mcmaster.se2aa4.island.team45.decision_stages.searching_island;

import ca.mcmaster.se2aa4.island.team45.decision_stages.Stage;
import ca.mcmaster.se2aa4.island.team45.decision_stages.StageManager;
import ca.mcmaster.se2aa4.island.team45.decision_stages.utility_substages.InPositionTurn;
import ca.mcmaster.se2aa4.island.team45.decision_stages.utility_substages.UTurn;
import ca.mcmaster.se2aa4.island.team45.decision_stages.utility_substages.flyDistance;
import ca.mcmaster.se2aa4.island.team45.drone.FlightCommands;
import ca.mcmaster.se2aa4.island.team45.drone.PreviousState;
import ca.mcmaster.se2aa4.island.team45.drone.battery.BatteryManager;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.map.POIManager;
import ca.mcmaster.se2aa4.island.team45.map.coordinates.CoordinateManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SearchInitial implements Stage { // The initial stage of the mission, find first edge of island
    private final Logger logger = LogManager.getLogger();
    private String finalEdgeLabel;

    public SearchInitial(String finalEdgeLabel) {
        this.finalEdgeLabel = finalEdgeLabel;
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
            if (poiManager.atIslandEdge(direction.getDirection().stringForward(), cm.getCoordinates(), direction.getDirection().getFullDirectionString(direction.getDirection().stringForward()))) {
                logger.info("** Reaching {} edge **", direction.getDirection().getFullDirectionString(direction.getDirection().stringForward()));

                if (poiManager.atIslandEdge(this.finalEdgeLabel, cm.getCoordinates(), direction.getDirection().getFullDirectionString(this.finalEdgeLabel))) {
                    logger.info("** Reached {} Edge **", this.finalEdgeLabel);
                    
                    String UTurnDirection;
                    if (pState.getOppositeUTurn().equals("right")) {
                        UTurnDirection = "left";
                    } else {
                        UTurnDirection = "right";
                    }

                    sm.setStage(new UTurn(UTurnDirection, new InPositionTurn(pState.getOppositeUTurn(), new SearchFinal())));
                    pState.setPrevAction("fly");
                    return FlightCommands.getInstance().fly();
                }

                pState.setPrevAction("fly");

                sm.setStage(new UTurn(pState.getOppositeUTurn(), new SearchInitial(finalEdgeLabel)));
                return FlightCommands.getInstance().fly();
            }

            if (pState.getPrevAction().equals("scan")) {
                logger.info("** Flying forward **");
                pState.setPrevAction("fly");
                return FlightCommands.getInstance().fly();
            } else {
                logger.info("** Scanning square **");
                pState.setPrevAction("scan");
                return FlightCommands.getInstance().scan();
            }

            
    }
}
