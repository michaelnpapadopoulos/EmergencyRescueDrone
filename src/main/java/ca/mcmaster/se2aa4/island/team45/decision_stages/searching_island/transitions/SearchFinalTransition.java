package ca.mcmaster.se2aa4.island.team45.decision_stages.searching_island.transitions;

import ca.mcmaster.se2aa4.island.team45.drone.commands.PreviousResult;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.map.interest_points.POIManager;
import ca.mcmaster.se2aa4.island.team45.decision_stages.Transition;
import ca.mcmaster.se2aa4.island.team45.decision_stages.StageManager;
import ca.mcmaster.se2aa4.island.team45.drone.commands.FlightCommands;
import ca.mcmaster.se2aa4.island.team45.drone.commands.PreviousDecision;
import ca.mcmaster.se2aa4.island.team45.map.coordinates.CoordinateManager;

public class SearchFinalTransition extends Transition implements Search {
    @Override
    public void execute(
        DirectionManager directionManager,
        POIManager poiManager,
        PreviousResult previousResult,
        StageManager stageManager,
        PreviousDecision previousDecision,
        CoordinateManager coordinateManager) {
            if (poiManager.atIslandEdge(directionManager.getDirection().stringForward(), 
                coordinateManager.getCoordinates(), 
                directionManager.getDirection().getFullDirectionString(directionManager.getDirection().stringForward()))) {
            
                // If drone is at the edge of the island and the perpendicular edge to the drone is the final edge, turn on the spot and transition to SearchFinal
                if (poiManager.atIslandEdge(stageManager.getTransitionInfo().getFinalEdgeDir().stringForward(), 
                    coordinateManager.getOffsetCoordinates(stageManager.getTransitionInfo().getFinalEdgeDir(), 1), 
                    directionManager.getDirection().getFullDirectionString(stageManager.getTransitionInfo().getFinalEdgeString()))) {
                        stageManager(new Stop());
                } else {

                }
            }
    }
}
