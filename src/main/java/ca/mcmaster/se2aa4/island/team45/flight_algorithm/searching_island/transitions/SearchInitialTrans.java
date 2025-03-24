package ca.mcmaster.se2aa4.island.team45.flight_algorithm.searching_island.transitions;

import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.flight_algorithm.*;
import ca.mcmaster.se2aa4.island.team45.flight_algorithm.searching_island.stages.*;
import ca.mcmaster.se2aa4.island.team45.flight_algorithm.utility_stages.*;
import ca.mcmaster.se2aa4.island.team45.map.interest_points.IslandEdgeManager;
import ca.mcmaster.se2aa4.island.team45.drone.PreviousResult;
import ca.mcmaster.se2aa4.island.team45.drone.commands.PreviousDecision;
import ca.mcmaster.se2aa4.island.team45.map.coordinates.CoordinateManager;

public class SearchInitialTrans extends Transition implements Search {

    /**************************************************************************
     * Checks if the drone is at the edge its facing if so does a uturn
     * if the drone reches a 1 or 2 shift it either does a a sequence of 
     * turning followed by entering search final or enters ed search after a 
     * uturn respectively, if over water transitions to fly distance
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
            
            TransitionInformation transitionInfo = algorithmManager.getTransitionInfo();
            if (Search.atForwardEdge(islandEdgeManager, directionManager, coordinateManager)) {
                    if (Search.shiftFromSideEdge(islandEdgeManager, transitionInfo.getSweepDir(), coordinateManager, 1)) {
                        
                        algorithmManager.setStage(
                            new UTurn(previousDecision.getOppositeUTurn(), 
                            new InPositionTurn(previousDecision.getOppositeUTurn(), // Possible fix to in position turn
                            new Scan())));
                        algorithmManager.setTransition(new InPositionTurnTrans(previousDecision.getOppositeUTurn(), directionManager.getDirection(), coordinateManager.getCoordinates()));

                    } else if (Search.shiftFromSideEdge(islandEdgeManager, transitionInfo.getSweepDir(), coordinateManager, 2)) {
                        
                        algorithmManager.setStage(
                            new UTurn(previousDecision.getOppositeUTurn(), 
                            new Scan()));
                        algorithmManager.setTransition(new EdgeSearchTrans());

                    } else {
                        algorithmManager.setStage(new UTurn(previousDecision.getOppositeUTurn(), new Scan()));
                    }
                } else if (previousDecision.getPrevAction().equals("scan") && Search.overWater(previousResult)) {
                    algorithmManager.setStage(new FlyDistance());
                    algorithmManager.setTransition(new FlyDistanceTrans(new SearchInitialTrans()));
                }
        }
}
