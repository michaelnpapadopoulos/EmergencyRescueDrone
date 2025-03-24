package ca.mcmaster.se2aa4.island.team45.flight_algorithm.finding_island.transitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.team45.drone.PreviousResult;
import ca.mcmaster.se2aa4.island.team45.drone.commands.PreviousDecision;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.flight_algorithm.AlgorithmManager;
import ca.mcmaster.se2aa4.island.team45.flight_algorithm.Transition;
import ca.mcmaster.se2aa4.island.team45.flight_algorithm.finding_island.stages.DirectionalSweep;
import ca.mcmaster.se2aa4.island.team45.map.coordinates.CoordinateManager;
import ca.mcmaster.se2aa4.island.team45.map.interest_points.IslandEdgeManager;

public class EchoSweepTrans extends Transition {
    private final Logger logger = LogManager.getLogger();
    
    @Override
    public void execute(
        DirectionManager directionManager,
        IslandEdgeManager islandEdgeManager,
        PreviousResult previousResult,
        AlgorithmManager algorithmManager,
        PreviousDecision previousDecision,
        CoordinateManager coordinateManager) {
            if (previousResult.getFound("GROUND")) {
                logger.info("** FOUND EDGE");
                islandEdgeManager.addEdge(directionManager, coordinateManager.getCoordinates());
                String sweepDir = previousDecision.getPrevHeading();
                algorithmManager.getTransitionInfo().setSweepDir(sweepDir);
                algorithmManager.setStage(new DirectionalSweep(sweepDir));
                algorithmManager.setTransition(new FindEdgeTrans());
            }
    }
}
