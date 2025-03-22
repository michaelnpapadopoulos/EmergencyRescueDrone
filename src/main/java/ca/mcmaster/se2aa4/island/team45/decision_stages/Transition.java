package ca.mcmaster.se2aa4.island.team45.decision_stages;

import ca.mcmaster.se2aa4.island.team45.decision_stages.utility_substages.*;
import ca.mcmaster.se2aa4.island.team45.drone.battery.BatteryManager;
import ca.mcmaster.se2aa4.island.team45.drone.commands.PreviousResult;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.map.interest_points.POIManager;
import ca.mcmaster.se2aa4.island.team45.drone.commands.PreviousDecision;
import ca.mcmaster.se2aa4.island.team45.map.coordinates.CoordinateManager;

public abstract class Transition { // Template Method Pattern
    public void makeTransition(
        DirectionManager directionManager,
        BatteryManager batteryManager,
        POIManager poiManager,
        PreviousResult previousResult,
        StageManager stageManager,
        PreviousDecision previousDecision,
        CoordinateManager coordinateManager) {
            if (checksBatteryCritical(batteryManager)) {
                stageManager.setStage(new Stop());
            } else {
                execute(directionManager, poiManager, previousResult, stageManager, previousDecision, coordinateManager);
            }
        }

    public boolean checksBatteryCritical(BatteryManager batteryManager) {
        if (batteryManager.getBatteryLevel() < 50) {
            return true;
        }

        return false;
    }

    public abstract void execute(DirectionManager directionManager,
        POIManager poiManager,
        PreviousResult previousResult,
        StageManager stageManager,
        PreviousDecision previousDecision,
        CoordinateManager coordinateManager);
}
