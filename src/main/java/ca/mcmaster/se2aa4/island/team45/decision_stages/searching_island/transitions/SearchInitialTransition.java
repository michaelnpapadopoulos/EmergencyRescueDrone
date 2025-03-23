package ca.mcmaster.se2aa4.island.team45.decision_stages.searching_island.transitions;

import ca.mcmaster.se2aa4.island.team45.drone.commands.PreviousResult;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.map.interest_points.POIManager;
import ca.mcmaster.se2aa4.island.team45.decision_stages.Transition;
import ca.mcmaster.se2aa4.island.team45.decision_stages.searching_island.SearchFinal;
import ca.mcmaster.se2aa4.island.team45.decision_stages.searching_island.SearchInitial;
import ca.mcmaster.se2aa4.island.team45.decision_stages.utility_substages.EdgeFlight;
import ca.mcmaster.se2aa4.island.team45.decision_stages.utility_substages.InPositionTurn;
import ca.mcmaster.se2aa4.island.team45.decision_stages.utility_substages.UTurn;

import org.json.JSONArray;

import ca.mcmaster.se2aa4.island.team45.decision_stages.StageManager;
import ca.mcmaster.se2aa4.island.team45.drone.commands.PreviousDecision;
import ca.mcmaster.se2aa4.island.team45.map.coordinates.CoordinateManager;

public class SearchInitialTransition extends Transition implements Search {
    @Override
    public void execute(
        DirectionManager directionManager,
        POIManager poiManager,
        PreviousResult previousResult,
        StageManager stageManager,
        PreviousDecision previousDecision,
        CoordinateManager coordinateManager) {
            if (poiManager.atIslandEdge(
                directionManager.getDirection().stringForward(), 
                coordinateManager.getCoordinates(), 
                directionManager.getDirection().getFullDirectionString(directionManager.getDirection().stringForward()))) {
                
                    if (poiManager.atIslandEdge(stageManager.getTransitionInfo().getFinalEdgeString(), 
                    coordinateManager.getOffsetCoordinates(stageManager.getTransitionInfo().getFinalEdgeDir(), 1), 
                    directionManager.getDirection().getFullDirectionString(stageManager.getTransitionInfo().getFinalEdgeString()))) {

                        String inPositionTurnDirection;
                        if (previousDecision.getOppositeUTurn().equals("right")) {
                            inPositionTurnDirection = "left";
                        } else {
                            inPositionTurnDirection = "right";
                        }
                        
                        stageManager.getTransitionInfo().setFinalEdge(stageManager.getTransitionInfo().getFinalEdgeDir().getOppositeDirection().stringForward());
                        stageManager.setStage(new UTurn(previousDecision.getOppositeUTurn(), 
                            new InPositionTurn(inPositionTurnDirection, 
                                new SearchFinal(stageManager.getTransitionInfo().getFinalEdgeDir().getOppositeDirection()))));

                    } else if (poiManager.atIslandEdge(stageManager.getTransitionInfo().getFinalEdgeString(), 
                    coordinateManager.getOffsetCoordinates(stageManager.getTransitionInfo().getFinalEdgeDir(), 2), 
                    directionManager.getDirection().getFullDirectionString(stageManager.getTransitionInfo().getFinalEdgeString()))) {
                        
                        stageManager.setStage(new UTurn(previousDecision.getOppositeUTurn(), 
                            new EdgeFlight(stageManager.getTransitionInfo().getFinalEdgeString(), 
                                new SearchFinal(stageManager.getTransitionInfo().getFinalEdgeDir().getOppositeDirection()))));
                    } else {

                        stageManager.setStage(new UTurn(previousDecision.getOppositeUTurn(), new SearchInitial(stageManager.getTransitionInfo().getFinalEdgeString())));
                    }
                } else if (pState.getPrevAction().equals("scan") && overWater())
        }
}
