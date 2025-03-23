package ca.mcmaster.se2aa4.island.team45.decision_stages.finding_island.transitions;

import ca.mcmaster.se2aa4.island.team45.drone.commands.PreviousResult;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.map.interest_points.POIManager;
import ca.mcmaster.se2aa4.island.team45.decision_stages.Transition;
import ca.mcmaster.se2aa4.island.team45.decision_stages.TransitionInformation;
import ca.mcmaster.se2aa4.island.team45.decision_stages.finding_island.DirectionalSweep;
import ca.mcmaster.se2aa4.island.team45.decision_stages.StageManager;
import ca.mcmaster.se2aa4.island.team45.drone.commands.PreviousDecision;
import ca.mcmaster.se2aa4.island.team45.decision_stages.utility_substages.*;
import ca.mcmaster.se2aa4.island.team45.decision_stages.searching_island.SearchInitial;
import ca.mcmaster.se2aa4.island.team45.map.coordinates.CoordinateManager;
import ca.mcmaster.se2aa4.island.team45.decision_stages.finding_island.DirectionalSweep;

public class FindEdgeTransition extends Transition {
    @Override
    public void execute(
        DirectionManager directionManager,
        POIManager poiManager,
        PreviousResult previousResult,
        StageManager stageManager,
        PreviousDecision previousDecision,
        CoordinateManager coordinateManager) {
            if (previousResult.getFound() != null && previousResult.getFound().equals("OUT_OF_RANGE")) {
                String turnDirection;
                        if (directionManager.getDirection().stringRight().equals(stageManager.getTransitionInfo().getSweepDir())) {
                            turnDirection = "right";
                        } else {
                            turnDirection = "left";
                        }
                if (poiManager.numberOfIslandEdgesFound() == 4) {
                        poiManager.labelIslandEdges();

                        stageManager.getTransitionInfo().setFinalEdge(stageManager.getTransitionInfo().getSweepString());
                        stageManager.setStage(new UTurn(turnDirection, new SearchInitial(stageManager.getTransitionInfo().getSweepString())));
                } else {
                    if (turnDirection.equals("right")) {

                        stageManager.getTransitionInfo().setSweepDir(stageManager.getTransitionInfo().getSweepDir().stringRight());
                        stageManager.setStage(new Turn(turnDirection, new DirectionalSweep(stageManager.getTransitionInfo().getSweepDir().stringRight())));

                    } else {

                        stageManager.getTransitionInfo().setSweepDir(stageManager.getTransitionInfo().getSweepDir().stringLeft());
                        stageManager.setStage(new Turn(turnDirection, new DirectionalSweep(stageManager.getTransitionInfo().getSweepDir().stringLeft())));
                    }
                }
            }
        }
}
