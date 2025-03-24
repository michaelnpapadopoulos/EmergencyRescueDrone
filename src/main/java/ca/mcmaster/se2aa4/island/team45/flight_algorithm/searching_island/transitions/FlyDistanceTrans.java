package ca.mcmaster.se2aa4.island.team45.flight_algorithm.searching_island.transitions;

import ca.mcmaster.se2aa4.island.team45.drone.PreviousResult;
import ca.mcmaster.se2aa4.island.team45.drone.commands.PreviousDecision;
import ca.mcmaster.se2aa4.island.team45.drone.direction.*;
import ca.mcmaster.se2aa4.island.team45.flight_algorithm.AlgorithmManager;
import ca.mcmaster.se2aa4.island.team45.flight_algorithm.Transition;
import ca.mcmaster.se2aa4.island.team45.flight_algorithm.searching_island.stages.Scan;
import ca.mcmaster.se2aa4.island.team45.map.coordinates.*;
import ca.mcmaster.se2aa4.island.team45.map.interest_points.IslandEdgeManager;

public class FlyDistanceTrans extends Transition {
    private int distance;
    private Transition previousTransition;

    public FlyDistanceTrans(Transition previousTransition) {
                this.previousTransition = previousTransition;
    }

    public void execute(
        DirectionManager directionManager,
        IslandEdgeManager islandEdgeManager,
        PreviousResult previousResult,
        AlgorithmManager algorithmManager,
        PreviousDecision previousDecision,
        CoordinateManager coordinateManager) {

            if(previousDecision.getPrevAction().equals("echo")) {
                if(previousResult.getFound("OUT_OF_RANGE")) {
                    Direction facingDirection = directionManager.getDirection();
                    Coordinate facingEdge = islandEdgeManager.getEdge(facingDirection.toFullString());
                    
                    if (facingDirection.equals("N") || facingDirection.equals("S")) {
                        distance = Math.abs(coordinateManager.getCoordinates().getY() - facingEdge.getY());
                    } else {
                        distance = Math.abs(coordinateManager.getCoordinates().getX() - facingEdge.getX());
                    }
                    
                } else {
                    distance = previousResult.getRange();
                }
            } else {
                if (distance > 1) {
                    distance--;
                } else {
                    algorithmManager.setStage(new Scan());
                    algorithmManager.setTransition(previousTransition);
                }
            }
        }
    
}
