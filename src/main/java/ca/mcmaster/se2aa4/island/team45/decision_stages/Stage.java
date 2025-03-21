package ca.mcmaster.se2aa4.island.team45.decision_stages;

import ca.mcmaster.se2aa4.island.team45.drone.BatteryManager;
import ca.mcmaster.se2aa4.island.team45.drone.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.map.POIManager;

public interface Stage {
    String makeDecision(DirectionManager direction, BatteryManager battery, PreviousState previousState, StageManager stageManager, POIManager poiManager, CoordinateManager coordinateManager);
}