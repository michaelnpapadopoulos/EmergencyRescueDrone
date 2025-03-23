package ca.mcmaster.se2aa4.island.team45.decision_stages.searching_island.transitions;

import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.map.interest_points.IslandEdgeManager;
import ca.mcmaster.se2aa4.island.team45.decision_stages.Transition;
import ca.mcmaster.se2aa4.island.team45.decision_stages.StageManager;
import ca.mcmaster.se2aa4.island.team45.drone.PreviousResult;
import ca.mcmaster.se2aa4.island.team45.drone.commands.PreviousDecision;
import ca.mcmaster.se2aa4.island.team45.map.coordinates.CoordinateManager;
import ca.mcmaster.se2aa4.island.team45.decision_stages.searching_island.*;
import ca.mcmaster.se2aa4.island.team45.map.coordinates.Coordinate;
import ca.mcmaster.se2aa4.island.team45.drone.direction.*;

public class FlyDistanceTransition extends Transition {
    private int distance;
    private Transition previousTransition;

    public FlyDistanceTransition(Transition previousTransition) {
        this.previousTransition = previousTransition;
    }

    public void execute(
        DirectionManager directionManager,
        IslandEdgeManager islandEdgeManager,
        PreviousResult previousResult,
        StageManager stageManager,
        PreviousDecision previousDecision,
        CoordinateManager coordinateManager) {

            if(previousDecision.getPrevAction().equals("echo")) {
                if(previousResult.getFound().equals("OUT_OF_RANGE")) {
                    Direction facingDirection = directionManager.getDirection();
                    Coordinate facingEdge = islandEdgeManager.getEdge(facingDirection.getFullDirectionString());
                    
                    if (facingDirection.stringForward().equals("N") || facingDirection.stringForward().equals("S")) {
                        distance = Math.abs(coordinateManager.getCoordinates().getY().getCoordVal() - facingEdge.getY().getCoordVal());
                    } else {
                        distance = Math.abs(coordinateManager.getCoordinates().getX().getCoordVal() - facingEdge.getX().getCoordVal());
                    }
                    
                } else {
                    distance = previousResult.getRange();
                }
            } else {
                if (distance > 1) {
                    distance--;
                } else {
                    stageManager.setStage(new Scan());
                    stageManager.setTransition(previousTransition);
                }
            }
        }
    
}
