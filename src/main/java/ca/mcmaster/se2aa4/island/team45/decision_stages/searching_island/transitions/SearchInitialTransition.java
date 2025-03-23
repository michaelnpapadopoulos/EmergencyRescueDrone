package ca.mcmaster.se2aa4.island.team45.decision_stages.searching_island.transitions;

import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.map.interest_points.IslandEdgeManager;
import ca.mcmaster.se2aa4.island.team45.decision_stages.Transition;
import ca.mcmaster.se2aa4.island.team45.decision_stages.TransitionInformation;
import ca.mcmaster.se2aa4.island.team45.decision_stages.searching_island.Scan;
import ca.mcmaster.se2aa4.island.team45.decision_stages.utility_stages.*;
import ca.mcmaster.se2aa4.island.team45.decision_stages.StageManager;
import ca.mcmaster.se2aa4.island.team45.drone.PreviousResult;
import ca.mcmaster.se2aa4.island.team45.drone.commands.PreviousDecision;
import ca.mcmaster.se2aa4.island.team45.decision_stages.searching_island.FlyDistance;
import ca.mcmaster.se2aa4.island.team45.map.coordinates.CoordinateManager;

public class SearchInitialTransition extends Transition implements Search {
    @Override
    public void execute(
        DirectionManager directionManager,
        IslandEdgeManager islandEdgeManager,
        PreviousResult previousResult,
        StageManager stageManager,
        PreviousDecision previousDecision,
        CoordinateManager coordinateManager) {
            
            TransitionInformation transitionInfo = stageManager.getTransitionInfo();
            if (Search.atForwardEdge(islandEdgeManager, directionManager, coordinateManager)) {
                    if (Search.shiftFromSideEdge(islandEdgeManager, transitionInfo.getSweepDir(), coordinateManager, 1)) {
                        String inPositionTurnDirection;
                        if (previousDecision.getOppositeUTurn().equals("right")) {
                            inPositionTurnDirection = "left";
                        } else {
                            inPositionTurnDirection = "right";
                        }
                        
                        //transitionInfo.setFinalEdge(transitionInfo.getFinalEdgeDir().getOppositeDirection().stringForward());
                        stageManager.setStage(
                            new UTurn(previousDecision.getOppositeUTurn(), 
                            new InPositionTurn(previousDecision.getOppositeUTurn(), // Possible fix to in position turn
                            new Scan())));
                        stageManager.setTransition(new InPositionTurnTransition(previousDecision.getOppositeUTurn(), directionManager.getDirection(), coordinateManager.getCoordinates()));

                    } else if (Search.shiftFromSideEdge(islandEdgeManager, transitionInfo.getSweepDir(), coordinateManager, 2)) {
                        //transitionInfo.setFinalEdge(transitionInfo.getFinalEdgeDir().getOppositeDirection().stringForward());
                        stageManager.setStage(
                            new UTurn(previousDecision.getOppositeUTurn(), 
                            new Scan()));
                        stageManager.setTransition(new EdgeSearch());

                    } else {
                        stageManager.setStage(new UTurn(previousDecision.getOppositeUTurn(), new Scan()));
                    }

                } else if (previousDecision.getPrevAction().equals("scan") && Search.overWater(previousResult)) {
                    stageManager.setStage(new FlyDistance());
                    stageManager.setTransition(new FlyDistanceTransition(new SearchInitialTransition()));
                }
        }
}
