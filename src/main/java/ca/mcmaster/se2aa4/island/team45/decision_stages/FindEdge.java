package ca.mcmaster.se2aa4.island.team45.decision_stages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.team45.drone.BatteryManager;
import ca.mcmaster.se2aa4.island.team45.drone.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.drone.FlightCommands;
import ca.mcmaster.se2aa4.island.team45.drone.PreviousResult;
import ca.mcmaster.se2aa4.island.team45.map.CoordinateManager;
import ca.mcmaster.se2aa4.island.team45.map.POIManager;

public class FindEdge extends Stage {
    private final Logger logger = LogManager.getLogger();
    private int distanceToEdge;

    public FindEdge (int distanceToEdge) {
        this.distanceToEdge = distanceToEdge;
    }

    public String makeDecision(DirectionManager direction, BatteryManager battery, PreviousResult pResult, PreviousDecision pDecision, StageManager sm, POIManager poiManager, CoordinateManager cm) {
        logger.info("** Flying to the edge of the island **");
        if (distanceToEdge >= 0) {
            distanceToEdge--;
            pDecision.setPrevAction("fly");
            return FlightCommands.getInstance().fly();
        } else if (distanceToEdge == -1) {
            logger.info("** Reached the edge of the island **");
            distanceToEdge--;

            pDecision.setPrevAction("scan");
            return FlightCommands.getInstance().scan();
        } else {
            logger.info("** Scanned the edge of the island **");
            pDecision.setPrevAction("stop");
            return FlightCommands.getInstance().stop();
        }
    }

    
}