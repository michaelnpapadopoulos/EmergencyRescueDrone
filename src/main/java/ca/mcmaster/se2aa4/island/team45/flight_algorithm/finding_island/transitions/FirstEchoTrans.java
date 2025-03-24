package ca.mcmaster.se2aa4.island.team45.flight_algorithm.finding_island.transitions;

import ca.mcmaster.se2aa4.island.team45.drone.PreviousResult;
import ca.mcmaster.se2aa4.island.team45.drone.commands.PreviousDecision;
import ca.mcmaster.se2aa4.island.team45.drone.direction.Direction;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.flight_algorithm.AlgorithmManager;
import ca.mcmaster.se2aa4.island.team45.flight_algorithm.Transition;
import ca.mcmaster.se2aa4.island.team45.flight_algorithm.finding_island.stages.*;
import ca.mcmaster.se2aa4.island.team45.flight_algorithm.utility_stages.Turn;
import ca.mcmaster.se2aa4.island.team45.map.coordinates.CoordinateManager;
import ca.mcmaster.se2aa4.island.team45.map.interest_points.IslandEdgeManager;

public class FirstEchoTrans extends Transition {
    @Override
    public void execute(
        DirectionManager directionManager,
        IslandEdgeManager islandEdgeManager,
        PreviousResult previousResult,
        AlgorithmManager algorithmManager,
        PreviousDecision previousDecision,
        CoordinateManager coordinateManager) {

            Direction currDirection = directionManager.getDirection();
            if (previousResult.getFound("GROUND")) {
                algorithmManager.getTransitionInfo().setSweepDir(currDirection.toString());
                algorithmManager.setTransition(new FindCornerTrans());
                algorithmManager.setStage(new Turn("left", new DirectionalSweep(directionManager.getDirection().toString())));
            } else if (previousDecision.getPrevAction() != null){
                algorithmManager.setTransition(new EchoSweepTrans());
                algorithmManager.setStage(new EchoSweep());
            }
    }
}
