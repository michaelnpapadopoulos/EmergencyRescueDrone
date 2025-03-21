package ca.mcmaster.se2aa4.island.team45.decision_stages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.team45.drone.BatteryManager;
import ca.mcmaster.se2aa4.island.team45.drone.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.drone.FlightCommands;
import ca.mcmaster.se2aa4.island.team45.map.CoordinateManager;
import ca.mcmaster.se2aa4.island.team45.map.POIManager;

public class POIStage implements Stage {
    private final Logger logger = LogManager.getLogger();

    @Override
    public String makeDecision(DirectionManager direction, BatteryManager battery, PreviousState previousState, StageManager stageManager, POIManager poiManager, CoordinateManager coordinateManager) {
        logger.info("Found all island edges...");
        previousState.setPrevAction("stop");

        return FlightCommands.getInstance().stop();
    }
}