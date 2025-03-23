package ca.mcmaster.se2aa4.island.team45.decision_stages.finding_island.transitions;

import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.map.interest_points.IslandEdgeManager;
import ca.mcmaster.se2aa4.island.team45.decision_stages.Transition;
import ca.mcmaster.se2aa4.island.team45.decision_stages.TransitionInformation;
import ca.mcmaster.se2aa4.island.team45.decision_stages.finding_island.DirectionalSweep;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.team45.decision_stages.StageManager;
import ca.mcmaster.se2aa4.island.team45.decision_stages.searching_island.Scan;
import ca.mcmaster.se2aa4.island.team45.decision_stages.searching_island.transitions.SearchInitialTransition;
import ca.mcmaster.se2aa4.island.team45.decision_stages.utility_stages.*;
import ca.mcmaster.se2aa4.island.team45.drone.PreviousResult;
import ca.mcmaster.se2aa4.island.team45.drone.commands.PreviousDecision;
import ca.mcmaster.se2aa4.island.team45.map.coordinates.CoordinateManager;

public class FindEdgeTransition extends Transition {
    private final Logger logger = LogManager.getLogger();

    @Override
    public void execute(
        DirectionManager directionManager,
        IslandEdgeManager islandEdgeManager,
        PreviousResult previousResult,
        StageManager stageManager,
        PreviousDecision previousDecision,
        CoordinateManager coordinateManager) {

            TransitionInformation transitionInfo = stageManager.getTransitionInfo();
            if (previousResult.getFound() != null && previousResult.getFound().equals("OUT_OF_RANGE")) {
                logger.info("** FOUND EDGE");
                String turnDirection;
                islandEdgeManager.addEdge(directionManager, coordinateManager.getCoordinates());
                if (directionManager.getDirection().stringRight().equals(transitionInfo.getSweepString())) {
                    turnDirection = "right";
                } else {
                    turnDirection = "left";
                }
                if (islandEdgeManager.numberOfEdgesFound() == 4) {
                        islandEdgeManager.labelEdges();

                        transitionInfo.setFinalEdge(transitionInfo.getSweepString());
                        stageManager.setStage(new UTurn(turnDirection, new Scan()));
                        stageManager.setTransition(new SearchInitialTransition());
                } else {
                    if (turnDirection.equals("right")) {

                        transitionInfo.setSweepDir(transitionInfo.getSweepDir().stringRight());
                        stageManager.setStage(new Turn(turnDirection, new DirectionalSweep(transitionInfo.getSweepDir().stringForward())));
                        stageManager.setTransition(new DirectionalSweepTransition());
                    } else {

                        transitionInfo.setSweepDir(transitionInfo.getSweepDir().stringLeft());
                        stageManager.setStage(new Turn(turnDirection, new DirectionalSweep(transitionInfo.getSweepDir().stringForward())));
                        stageManager.setTransition(new DirectionalSweepTransition());
                    }
                }
            }
        }
}
