package ca.mcmaster.se2aa4.island.team45.decision_stages.searching_island.transitions;

import ca.mcmaster.se2aa4.island.team45.decision_stages.Transition;
import ca.mcmaster.se2aa4.island.team45.drone.direction.Direction;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.map.interest_points.IslandEdgeManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.team45.decision_stages.StageManager;
import ca.mcmaster.se2aa4.island.team45.drone.PreviousResult;
import ca.mcmaster.se2aa4.island.team45.drone.commands.PreviousDecision;
import ca.mcmaster.se2aa4.island.team45.map.coordinates.Coordinate;
import ca.mcmaster.se2aa4.island.team45.map.coordinates.CoordinateManager;


public class InPositionTurnTransition extends Transition {
    private final Logger logger = LogManager.getLogger();

    private Coordinate initialCoords;
    private Direction initialDirection;
    private String turnDirection;
    
    public InPositionTurnTransition(String turnDirection, Direction initialDirection, Coordinate currCoordinate) {
        this.turnDirection = turnDirection;
        this.initialDirection = initialDirection;
        this.initialCoords = currCoordinate;
    }
    
    @Override
    public void execute(
        DirectionManager directionManager,
        IslandEdgeManager islandEdgeManager,
        PreviousResult previousResult,
        StageManager stageManager,
        PreviousDecision previousDecision,
        CoordinateManager coordinateManager) {

            logger.info("** TURN DIRECTION {}", turnDirection);
            logger.info("** INITIAL DIRECTION {}", initialDirection.stringForward());
            logger.info("** INITIAL COORDINATES [{},{}]", initialCoords.getX().getCoordVal(), initialCoords.getY().getCoordVal());

            switch(turnDirection) {
                case "left":
                    switch(initialDirection.stringForward()) {
                        case "N":
                            if ((initialCoords.getX().getCoordVal()-1 == coordinateManager.getCoordinateX()) && (initialCoords.getY().getCoordVal() == coordinateManager.getCoordinateY())) {
                                stageManager.setTransition(new SearchFinalTransition());
                            }
                            break;
                        case "E":
                            if ((initialCoords.getX().getCoordVal() == coordinateManager.getCoordinateX()) && (initialCoords.getY().getCoordVal()-1 == coordinateManager.getCoordinateY())) {
                                stageManager.setTransition(new SearchFinalTransition());
                            } 
                            break;
                        case "S":
                            if ((initialCoords.getX().getCoordVal()+1 == coordinateManager.getCoordinateX()) && (initialCoords.getY().getCoordVal() == coordinateManager.getCoordinateY())) {
                                stageManager.setTransition(new SearchFinalTransition());
                            } 
                            break;
                        case "W":
                            if ((initialCoords.getX().getCoordVal() == coordinateManager.getCoordinateX()) && (initialCoords.getY().getCoordVal()+1 == coordinateManager.getCoordinateY())) {
                                stageManager.setTransition(new SearchFinalTransition());
                            }
                            break;
                    }
                    break;
                case "right":
                    switch(initialDirection.stringForward()) {
                        case "N":
                            if ((initialCoords.getX().getCoordVal()+1 == coordinateManager.getCoordinateX()) && (initialCoords.getY().getCoordVal() == coordinateManager.getCoordinateY())) {
                                stageManager.setTransition(new SearchFinalTransition());
                            }
                            break;
                        case "E":
                            if ((initialCoords.getX().getCoordVal() == coordinateManager.getCoordinateX()) && (initialCoords.getY().getCoordVal()+1 == coordinateManager.getCoordinateY())) {
                                stageManager.setTransition(new SearchFinalTransition());
                            } 
                            break;
                        case "S":
                            if ((initialCoords.getX().getCoordVal()-1 == coordinateManager.getCoordinateX()) && (initialCoords.getY().getCoordVal() == coordinateManager.getCoordinateY())) {
                                stageManager.setTransition(new SearchFinalTransition());
                            } 
                            break;
                        case "W":
                            if ((initialCoords.getX().getCoordVal() == coordinateManager.getCoordinateX()) && (initialCoords.getY().getCoordVal()-1 == coordinateManager.getCoordinateY())) {
                                stageManager.setTransition(new SearchFinalTransition());
                            }
                            break;
                    }
                    break;
            }  
    }
}
