package ca.mcmaster.se2aa4.island.team45.flight_algorithm.finding_island.transitions;

import ca.mcmaster.se2aa4.island.team45.drone.PreviousResult;
import ca.mcmaster.se2aa4.island.team45.drone.commands.PreviousDecision;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.flight_algorithm.AlgorithmManager;
import ca.mcmaster.se2aa4.island.team45.flight_algorithm.Transition;
import ca.mcmaster.se2aa4.island.team45.map.coordinates.*;
import ca.mcmaster.se2aa4.island.team45.map.interest_points.IslandEdgeManager;

public class DirectionalSweepTrans extends Transition {
    @Override
    public void execute(
        DirectionManager directionManager,
        IslandEdgeManager islandEdgeManager,
        PreviousResult previousResult,
        AlgorithmManager algorithmManager,
        PreviousDecision previousDecision,
        CoordinateManager coordinateManager) {
            Coordinate currentCoordinate = coordinateManager.getCoordinates();

            if (previousResult.getFound("GROUND")) {
                islandEdgeManager.addEdge(directionManager, CoordinateUtilities.getShiftedCoordinates(currentCoordinate, directionManager.getDirection(), -1));
                algorithmManager.getTransitionInfo().setSweepDir(previousDecision.getPrevHeading());
                algorithmManager.setTransition(new FindEdgeTrans());
            }
    }
}