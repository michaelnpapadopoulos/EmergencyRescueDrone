package ca.mcmaster.se2aa4.island.team45.decision_stages.searching_island;

import ca.mcmaster.se2aa4.island.team45.decision_stages.PreviousDecision;
import ca.mcmaster.se2aa4.island.team45.decision_stages.Stage;
import ca.mcmaster.se2aa4.island.team45.decision_stages.StageManager;
import ca.mcmaster.se2aa4.island.team45.drone.BatteryManager;
import ca.mcmaster.se2aa4.island.team45.drone.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.drone.FlightCommands;
import ca.mcmaster.se2aa4.island.team45.drone.PreviousResult;
import ca.mcmaster.se2aa4.island.team45.map.POIManager;
import ca.mcmaster.se2aa4.island.team45.map.coordinates.CoordinateManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SearchInitial extends Stage { // The initial stage of the mission, find first edge of island
    private final Logger logger = LogManager.getLogger();

    public String makeDecision(
        DirectionManager direction, 
        BatteryManager battery, 
        PreviousResult pResult, 
        PreviousDecision pDecision,
        StageManager sm, 
        POIManager poiManager, 
        CoordinateManager cm
        ) {
            logger.info("** Initial Search passby **");
            logger.info("Echo results at the edge of the island:\nType: {}, Range: {}", pResult.getFound(), pResult.getRange());

            return FlightCommands.getInstance().stop();
    }
}
