package ca.mcmaster.se2aa4.island.team45.decision_stages.finding_island.transitions;

import ca.mcmaster.se2aa4.island.team45.drone.commands.PreviousResult;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.map.interest_points.POIManager;
import ca.mcmaster.se2aa4.island.team45.decision_stages.Transition;
import ca.mcmaster.se2aa4.island.team45.decision_stages.StageManager;
import ca.mcmaster.se2aa4.island.team45.decision_stages.finding_island.FindEdge;
import ca.mcmaster.se2aa4.island.team45.drone.commands.PreviousDecision;
import ca.mcmaster.se2aa4.island.team45.map.coordinates.CoordinateManager;

public class EchoSweepTransition extends Transition {
    @Override
    public void execute(
        DirectionManager directionManager,
        POIManager poiManager,
        PreviousResult previousResult,
        StageManager stageManager,
        PreviousDecision previousDecision,
        CoordinateManager coordinateManager) {
            if (previousResult.getFound() != null && previousResult.getFound().equals("GROUND")) {
                stageManager.getTransitionInfo().setSweepDir(previousDecision.getPrevHeading());
                stageManager.setStage(new FindEdge());
            }
    }
}
