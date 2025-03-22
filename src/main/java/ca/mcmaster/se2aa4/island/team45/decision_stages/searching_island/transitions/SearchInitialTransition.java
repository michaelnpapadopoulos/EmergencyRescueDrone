package ca.mcmaster.se2aa4.island.team45.decision_stages.searching_island.transitions;

import ca.mcmaster.se2aa4.island.team45.drone.commands.PreviousResult;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.map.interest_points.POIManager;
import ca.mcmaster.se2aa4.island.team45.decision_stages.Transition;
import ca.mcmaster.se2aa4.island.team45.decision_stages.StageManager;
import ca.mcmaster.se2aa4.island.team45.drone.commands.PreviousDecision;
import ca.mcmaster.se2aa4.island.team45.map.coordinates.CoordinateManager;

public class SearchInitialTransition extends Transition {
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
                
        }
    }
}
