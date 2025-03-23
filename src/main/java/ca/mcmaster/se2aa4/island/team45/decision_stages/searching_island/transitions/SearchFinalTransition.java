package ca.mcmaster.se2aa4.island.team45.decision_stages.searching_island.transitions;

import ca.mcmaster.se2aa4.island.team45.drone.direction.Direction;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.map.interest_points.IslandEdgeManager;
import ca.mcmaster.se2aa4.island.team45.decision_stages.Transition;
import ca.mcmaster.se2aa4.island.team45.decision_stages.TransitionInformation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.team45.map.coordinates.Coordinate;
import ca.mcmaster.se2aa4.island.team45.decision_stages.StageManager;
import ca.mcmaster.se2aa4.island.team45.drone.PreviousResult;
import ca.mcmaster.se2aa4.island.team45.drone.commands.PreviousDecision;
import ca.mcmaster.se2aa4.island.team45.map.coordinates.CoordinateManager;
import ca.mcmaster.se2aa4.island.team45.decision_stages.searching_island.*;
import ca.mcmaster.se2aa4.island.team45.decision_stages.searching_island.FlyDistance;
import ca.mcmaster.se2aa4.island.team45.decision_stages.utility_stages.*;

public class SearchFinalTransition extends Transition implements Search {
        private final Logger logger = LogManager.getLogger();

    @Override
    public void execute(
        DirectionManager directionManager,
        IslandEdgeManager islandEdgeManager,
        PreviousResult previousResult,
        StageManager stageManager,
        PreviousDecision previousDecision,
        CoordinateManager coordinateManager) {            
            
            Coordinate currentCoords = coordinateManager.getCoordinates();
            TransitionInformation transitionInfo = stageManager.getTransitionInfo();

            if (Search.atForwardEdge(islandEdgeManager, directionManager, coordinateManager)) {
                // If drone is at the edge of the island and the perpendicular edge to the drone is the final edge, turn on the spot and transition to SearchFinal
                if (Search.shiftFromSideEdge(islandEdgeManager, transitionInfo.getFinalEdgeDir().getOppositeDirection(), coordinateManager, 1)) {
                    logger.info("** REACHED FINAL EDGE");
                    stageManager.setStage(new Stop());
                } else if (Search.shiftFromSideEdge(islandEdgeManager, transitionInfo.getFinalEdgeDir(), coordinateManager, 0)) {
                    stageManager.setStage(new UTurn(previousDecision.getPrevUTurn(), new Scan()));
                } else {
                    stageManager.setStage(new UTurn(previousDecision.getOppositeUTurn(), new Scan()));
                }
                
            } else if (previousDecision.getPrevAction().equals("scan") && Search.overWater(previousResult)) {

                stageManager.setStage(new FlyDistance());
                stageManager.setTransition(new FlyDistanceTransition(new SearchFinalTransition()));
            }
    }
}
