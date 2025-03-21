package ca.mcmaster.se2aa4.island.team45.decision_stages;

import ca.mcmaster.se2aa4.island.team45.drone.PreviousState;
import ca.mcmaster.se2aa4.island.team45.drone.battery.BatteryManager;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.map.POIManager;
import ca.mcmaster.se2aa4.island.team45.map.coordinates.CoordinateManager;

public interface Stage {
    String makeDecision(DirectionManager directionMan, BatteryManager battery, PreviousState previousState, StageManager stageManager, POIManager poiManager, CoordinateManager coordinateManager);
}