package ca.mcmaster.se2aa4.island.team45.flight_algorithm.searching_island.transitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.team45.drone.PreviousResult;
import ca.mcmaster.se2aa4.island.team45.drone.commands.PreviousDecision;
import ca.mcmaster.se2aa4.island.team45.drone.direction.Direction;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.flight_algorithm.AlgorithmManager;
import ca.mcmaster.se2aa4.island.team45.flight_algorithm.Transition;
import ca.mcmaster.se2aa4.island.team45.map.coordinates.Coordinate;
import ca.mcmaster.se2aa4.island.team45.map.coordinates.CoordinateManager;
import ca.mcmaster.se2aa4.island.team45.map.interest_points.IslandEdgeManager;

public class InPositionTurnTrans extends Transition {
    private final Logger logger = LogManager.getLogger();

    private Coordinate initialCoords;
    private Direction initialDirection;
    private String turnDirection;
 
    /**************************************************************************
     * In position turn trans constructor
     * 
     * @param turnDirection the direction to in position turn
     * @param initialDirection the drones initial direction at the in position
     * turn
     * @param initialCoordinate the drones initial coordinsates at the in
     * position turn
    **************************************************************************/
    public InPositionTurnTrans(String turnDirection, Direction initialDirection, Coordinate initialCoordinate) {
        this.turnDirection = turnDirection;
        this.initialDirection = initialDirection;
        this.initialCoords = initialCoordinate;
    }

    /**************************************************************************
     * After the in position turn is completed Transition into final search
     * 
     * @param directionMan the drones direction manager
     * @param islandEdgeManager the programs islandEdgeManager
     * @param perviousResult the drones previous result
     * @param algorithmManager the programs algorithm manager
     * @param previousDecision the drones previous decision
     * @param coordinateManager the drones coordinate manager
    **************************************************************************/
    @Override
    public void execute(
        DirectionManager directionManager,
        IslandEdgeManager islandEdgeManager,
        PreviousResult previousResult,
        AlgorithmManager algorithmManager,
        PreviousDecision previousDecision,
        CoordinateManager coordinateManager) {

            logger.info("** TURN DIRECTION {}", turnDirection);
            logger.info("** INITIAL DIRECTION {}", initialDirection.toString());
            logger.info("** INITIAL COORDINATES [{},{}]", initialCoords.getX(), initialCoords.getY());
            
            int initialX = initialCoords.getX();
            int initialY = initialCoords.getY();

            int currentX = coordinateManager.getCoordinates().getX();
            int currentY = coordinateManager.getCoordinates().getY();

            switch(turnDirection) {
                case "left":
                    switch(initialDirection.toString()) {
                        case "N":
                            if ((initialX-1 == currentX) && (initialY == currentY)) {
                                algorithmManager.setTransition(new SearchFinalTrans());
                            }
                            break;
                        case "E":
                            if ((initialX == currentX) && (initialY-1 == currentY)) {
                                algorithmManager.setTransition(new SearchFinalTrans());
                            } 
                            break;
                        case "S":
                            if ((initialX+1 == currentX) && (initialY == currentY)) {
                                algorithmManager.setTransition(new SearchFinalTrans());
                            } 
                            break;
                        case "W":
                            if ((initialX == currentX) && (initialY+1 == currentY)) {
                                algorithmManager.setTransition(new SearchFinalTrans());
                            }
                            break;
                    }
                    break;
                case "right":
                    switch(initialDirection.toString()) {
                        case "N":
                            if ((initialX+1 == currentX) && (initialY == currentY)) {
                                algorithmManager.setTransition(new SearchFinalTrans());
                            }
                            break;
                        case "E":
                            if ((initialX == currentX) && (initialY+1 == currentY)) {
                                algorithmManager.setTransition(new SearchFinalTrans());
                            } 
                            break;
                        case "S":
                            if ((initialX-1 == currentX) && (initialY == currentY)) {
                                algorithmManager.setTransition(new SearchFinalTrans());
                            } 
                            break;
                        case "W":
                            if ((initialX == currentX) && (initialY-1 == currentY)) {
                                algorithmManager.setTransition(new SearchFinalTrans());
                            }
                            break;
                    }
                    break;
            }  
    }
}
