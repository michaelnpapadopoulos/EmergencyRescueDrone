package ca.mcmaster.se2aa4.island.team45.flight_algorithm.searching_island.transitions;

import ca.mcmaster.se2aa4.island.team45.drone.direction.*;
import ca.mcmaster.se2aa4.island.team45.flight_algorithm.*;
import ca.mcmaster.se2aa4.island.team45.flight_algorithm.searching_island.stages.*;
import ca.mcmaster.se2aa4.island.team45.flight_algorithm.utility_stages.*;
import ca.mcmaster.se2aa4.island.team45.map.interest_points.IslandEdgeManager;
import ca.mcmaster.se2aa4.island.team45.drone.PreviousResult;
import ca.mcmaster.se2aa4.island.team45.drone.commands.PreviousDecision;
import ca.mcmaster.se2aa4.island.team45.map.coordinates.CoordinateManager;

public class EdgeSearchTrans extends Transition implements Search {

    @Override
    public void execute(
        DirectionManager directionManager,
        IslandEdgeManager islandEdgeManager,
        PreviousResult previousResult,
        AlgorithmManager algorithmManager,
        PreviousDecision previousDecision,
        CoordinateManager coordinateManager) {
            TransitionInformation transitionInfo = algorithmManager.getTransitionInfo();
            Direction currentDirection = directionManager.getDirection();

            if (Search.atForwardEdge(islandEdgeManager, directionManager, coordinateManager)) {
                if (Search.shiftFromSideEdge(islandEdgeManager, transitionInfo.getFinalEdgeDir(), coordinateManager, 0)) {
                    algorithmManager.setStage(
                        new UTurn(previousDecision.getPrevUTurn(), 
                        new InPositionTurn(previousDecision.getPrevUTurn(), 
                        new Scan())));
                    algorithmManager.setTransition(
                        new InPositionTurnTrans(previousDecision.getPrevUTurn(), 
                        currentDirection, 
                        coordinateManager.getCoordinates()));
                }
            } else if (previousDecision.getPrevAction().equals("scan") && Search.overWater(previousResult)) {

                algorithmManager.setStage(new FlyDistance());
                algorithmManager.setTransition(new FlyDistanceTrans(new EdgeSearchTrans()));
            }
        }
}
