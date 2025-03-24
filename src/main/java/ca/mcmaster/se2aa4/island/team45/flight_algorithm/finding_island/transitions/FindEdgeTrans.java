package ca.mcmaster.se2aa4.island.team45.flight_algorithm.finding_island.transitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.team45.drone.PreviousResult;
import ca.mcmaster.se2aa4.island.team45.drone.commands.PreviousDecision;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.flight_algorithm.AlgorithmManager;
import ca.mcmaster.se2aa4.island.team45.flight_algorithm.Transition;
import ca.mcmaster.se2aa4.island.team45.flight_algorithm.TransitionInformation;
import ca.mcmaster.se2aa4.island.team45.flight_algorithm.finding_island.stages.DirectionalSweep;
import ca.mcmaster.se2aa4.island.team45.flight_algorithm.searching_island.stages.Scan;
import ca.mcmaster.se2aa4.island.team45.flight_algorithm.searching_island.transitions.SearchInitialTrans;
import ca.mcmaster.se2aa4.island.team45.flight_algorithm.utility_stages.Turn;
import ca.mcmaster.se2aa4.island.team45.flight_algorithm.utility_stages.UTurn;
import ca.mcmaster.se2aa4.island.team45.map.coordinates.Coordinate;
import ca.mcmaster.se2aa4.island.team45.map.coordinates.CoordinateManager;
import ca.mcmaster.se2aa4.island.team45.map.interest_points.IslandEdgeManager;

public class FindEdgeTrans extends Transition {
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
            Coordinate currentCoordinate = coordinateManager.getCoordinates();

            if (previousResult.getFound("OUT_OF_RANGE")) {
                logger.info("** FOUND EDGE");
                String turnDirection;
                islandEdgeManager.addEdge(directionManager, currentCoordinate); 
                
                if (directionManager.getDirection().getRight().equals(transitionInfo.getSweepString())) {
                    turnDirection = "right";
                } else {
                    turnDirection = "left";
                }
                if (islandEdgeManager.numberOfEdgesFound() == 4) { // If all edges have been found
                        islandEdgeManager.labelEdges();

                        transitionInfo.setFinalEdge(transitionInfo.getSweepString());
                        algorithmManager.setStage(new UTurn(turnDirection, new Scan()));
                        algorithmManager.setTransition(new SearchInitialTrans());
                } else {
                    if (turnDirection.equals("right")) {

                        transitionInfo.setSweepDir(transitionInfo.getSweepDir().getRight().toString());
                        algorithmManager.setStage(new Turn(turnDirection, new DirectionalSweep(transitionInfo.getSweepString())));
                        algorithmManager.setTransition(new DirectionalSweepTrans());
                    } else {

                        transitionInfo.setSweepDir(transitionInfo.getSweepDir().getLeft().toString());
                        algorithmManager.setStage(new Turn(turnDirection, new DirectionalSweep(transitionInfo.getSweepString())));
                        algorithmManager.setTransition(new DirectionalSweepTrans());
                    }
                }
            }
        }
}
