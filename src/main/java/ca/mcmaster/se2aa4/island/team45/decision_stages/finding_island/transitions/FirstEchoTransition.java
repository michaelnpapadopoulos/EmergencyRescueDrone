package ca.mcmaster.se2aa4.island.team45.decision_stages.finding_island.transitions;

import ca.mcmaster.se2aa4.island.team45.drone.commands.PreviousResult;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.map.interest_points.POIManager;
import ca.mcmaster.se2aa4.island.team45.decision_stages.Transition;
import ca.mcmaster.se2aa4.island.team45.decision_stages.StageManager;
import ca.mcmaster.se2aa4.island.team45.decision_stages.finding_island.EchoSweep;
import ca.mcmaster.se2aa4.island.team45.decision_stages.finding_island.FindEdge;
import ca.mcmaster.se2aa4.island.team45.decision_stages.utility_substages.*;
import ca.mcmaster.se2aa4.island.team45.drone.commands.PreviousDecision;
import ca.mcmaster.se2aa4.island.team45.map.coordinates.CoordinateManager;

public class FirstEchoTransition extends Transition {
    @Override
    public void execute(
        DirectionManager directionManager,
        POIManager poiManager,
        PreviousResult previousResult,
        StageManager stageManager,
        PreviousDecision previousDecision) {
            if (previousResult.getFound() != null && previousResult.getFound().equals("GROUND")) {
                stageManager.setStage(new Turn("left", new FindEdge()));
            } else {
                stageManager.setStage(new EchoSweep());
            }
    }
}
