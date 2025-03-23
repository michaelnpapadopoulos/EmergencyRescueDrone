package ca.mcmaster.se2aa4.island.team45.decision_stages.finding_island.transitions;

import ca.mcmaster.se2aa4.island.team45.decision_stages.StageManager;
import ca.mcmaster.se2aa4.island.team45.decision_stages.Transition;
import ca.mcmaster.se2aa4.island.team45.decision_stages.finding_island.DirectionalSweep;
import ca.mcmaster.se2aa4.island.team45.drone.PreviousResult;
import ca.mcmaster.se2aa4.island.team45.drone.commands.PreviousDecision;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionEnum;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.map.coordinates.CoordinateManager;
import ca.mcmaster.se2aa4.island.team45.map.interest_points.IslandEdgeManager;

public class EchoSweepTransition extends Transition {
    @Override
    public void execute(
        DirectionManager directionManager,
        IslandEdgeManager islandEdgeManager,
        PreviousResult previousResult,
        StageManager stageManager,
        PreviousDecision previousDecision,
        CoordinateManager coordinateManager) {
            if (previousResult.getFound() != null && previousResult.getFound().equals("GROUND")) {
                islandEdgeManager.addEdge(directionManager, coordinateManager.getCoordinates());
                DirectionEnum sweepDir = DirectionEnum.fromString(previousDecision.getPrevHeading());
                stageManager.getTransitionInfo().setSweepDir(sweepDir);
                stageManager.setStage(new DirectionalSweep(sweepDir));
                stageManager.setTransition(new FindEdgeTransition());
            }
    }
}