package ca.mcmaster.se2aa4.island.team45.decision_stages.searching_island.transitions;

import ca.mcmaster.se2aa4.island.team45.decision_stages.Transition;
import ca.mcmaster.se2aa4.island.team45.drone.direction.Direction;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.map.interest_points.IslandEdgeManager;
import ca.mcmaster.se2aa4.island.team45.decision_stages.TransitionInformation;
import ca.mcmaster.se2aa4.island.team45.decision_stages.searching_island.Scan;
import ca.mcmaster.se2aa4.island.team45.decision_stages.utility_stages.*;
import ca.mcmaster.se2aa4.island.team45.decision_stages.StageManager;
import ca.mcmaster.se2aa4.island.team45.drone.PreviousResult;
import ca.mcmaster.se2aa4.island.team45.drone.commands.PreviousDecision;
import ca.mcmaster.se2aa4.island.team45.map.coordinates.CoordinateManager;
import ca.mcmaster.se2aa4.island.team45.decision_stages.searching_island.FlyDistance;

public class EdgeSearch extends Transition implements Search {

    @Override
    public void execute(
        DirectionManager directionManager,
        IslandEdgeManager islandEdgeManager,
        PreviousResult previousResult,
        StageManager stageManager,
        PreviousDecision previousDecision,
        CoordinateManager coordinateManager) {
            TransitionInformation transitionInfo = stageManager.getTransitionInfo();
            Direction currentDirection = directionManager.getDirection();

            if (Search.atForwardEdge(islandEdgeManager, directionManager, coordinateManager)) {
                if (Search.shiftFromSideEdge(islandEdgeManager, transitionInfo.getFinalEdgeDir(), coordinateManager, 0)) {
                    stageManager.setStage(
                        new UTurn(previousDecision.getPrevUTurn(), 
                        new InPositionTurn(previousDecision.getPrevUTurn(), 
                        new Scan())));
                    stageManager.setTransition(
                        new InPositionTurnTransition(previousDecision.getPrevUTurn(), 
                        currentDirection, 
                        coordinateManager.getCoordinates()));
                }
            } else if (previousDecision.getPrevAction().equals("scan") && Search.overWater(previousResult)) {

                stageManager.setStage(new FlyDistance());
                stageManager.setTransition(new FlyDistanceTransition(new EdgeSearch()));
            }
        }
}
