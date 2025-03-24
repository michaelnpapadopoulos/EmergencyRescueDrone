package ca.mcmaster.se2aa4.island.team45.flight_algorithm.searching_island.transitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.team45.map.interest_points.IslandEdgeManager;
import ca.mcmaster.se2aa4.island.team45.drone.PreviousResult;
import ca.mcmaster.se2aa4.island.team45.drone.commands.PreviousDecision;
import ca.mcmaster.se2aa4.island.team45.map.coordinates.CoordinateManager;
import ca.mcmaster.se2aa4.island.team45.drone.direction.*;
import ca.mcmaster.se2aa4.island.team45.flight_algorithm.*;
import ca.mcmaster.se2aa4.island.team45.flight_algorithm.searching_island.stages.*;
import ca.mcmaster.se2aa4.island.team45.flight_algorithm.utility_stages.*;

public class SearchFinalTrans extends Transition implements Search {
    private final Logger logger = LogManager.getLogger();

    @Override
    public void execute(
        DirectionManager directionManager,
        IslandEdgeManager islandEdgeManager,
        PreviousResult previousResult,
        AlgorithmManager algorithmManager,
        PreviousDecision previousDecision,
        CoordinateManager coordinateManager) {            
            TransitionInformation transitionInfo = algorithmManager.getTransitionInfo();

            if (Search.atForwardEdge(islandEdgeManager, directionManager, coordinateManager)) {
                // If drone is at the edge of the island and the perpendicular edge to the drone is the final edge, turn on the spot and transition to SearchFinal
                if (Search.shiftFromSideEdge(islandEdgeManager, DirectionUtilities.getOpposite(transitionInfo.getFinalEdgeDir()), coordinateManager, 1)) {
                    logger.info("** REACHED FINAL EDGE");
                    algorithmManager.setStage(new Stop());
                } else if (Search.shiftFromSideEdge(islandEdgeManager, transitionInfo.getFinalEdgeDir(), coordinateManager, 0)) {
                    algorithmManager.setStage(new UTurn(previousDecision.getPrevUTurn(), new Scan()));
                } else {
                    algorithmManager.setStage(new UTurn(previousDecision.getOppositeUTurn(), new Scan()));
                }
                
            } else if (previousDecision.getPrevAction().equals("scan") && Search.overWater(previousResult)) {

                algorithmManager.setStage(new FlyDistance());
                algorithmManager.setTransition(new FlyDistanceTrans(new SearchFinalTrans()));
            }
    }
}
