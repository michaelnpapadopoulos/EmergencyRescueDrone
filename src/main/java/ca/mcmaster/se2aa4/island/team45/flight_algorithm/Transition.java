package ca.mcmaster.se2aa4.island.team45.flight_algorithm;

import ca.mcmaster.se2aa4.island.team45.drone.PreviousResult;
import ca.mcmaster.se2aa4.island.team45.drone.battery.BatteryManager;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.flight_algorithm.utility_stages.*;
import ca.mcmaster.se2aa4.island.team45.map.interest_points.IslandEdgeManager;
import ca.mcmaster.se2aa4.island.team45.drone.commands.PreviousDecision;
import ca.mcmaster.se2aa4.island.team45.map.coordinates.CoordinateManager;

public abstract class Transition { // Template Method Pattern

    /**************************************************************************
     * Uses the coordinate to determine whether to care about the X or Y 
     * coordinate
     * 
     * @param directionMan the drones direction manager
     * @param coord the drones current coordinates
    **************************************************************************/
    public void makeTransition(
        DirectionManager directionManager,
        BatteryManager batteryManager,
        IslandEdgeManager islandEdgeManager,
        PreviousResult previousResult,
        AlgorithmManager algorithmManager,
        PreviousDecision previousDecision,
        CoordinateManager coordinateManager) {
            if (checkBatteryCritical(batteryManager)) {
                algorithmManager.setStage(new Stop());
            } else {
                execute(directionManager, islandEdgeManager, previousResult, algorithmManager, previousDecision, coordinateManager);
            }
        }

    /**************************************************************************
     * Uses the coordinate to determine whether to care about the X or Y 
     * coordinate
     * 
     * @param directionMan the drones direction manager
     * @param coord the drones current coordinates
    **************************************************************************/
    public abstract void execute(
        DirectionManager directionManager,
        IslandEdgeManager islandEdgeManager,
        PreviousResult previousResult,
        AlgorithmManager algorithmManager,
        PreviousDecision previousDecision,
        CoordinateManager coordinateManager);
    
    /**************************************************************************
     * Uses the coordinate to determine whether to care about the X or Y 
     * coordinate
     * 
     * @param directionMan the drones direction manager
     * @param coord the drones current coordinates
    **************************************************************************/
    private boolean checkBatteryCritical(BatteryManager batteryManager) {
        if (batteryManager.getBatteryLevel() < 60) {
            return true;
        }

        return false;
    }
}
