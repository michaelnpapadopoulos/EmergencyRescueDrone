package ca.mcmaster.se2aa4.island.team45.decision_stages;

import ca.mcmaster.se2aa4.island.team45.drone.BatteryManager;
import ca.mcmaster.se2aa4.island.team45.drone.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.drone.FlightCommands;
import ca.mcmaster.se2aa4.island.team45.drone.PreviousResult;
import ca.mcmaster.se2aa4.island.team45.map.CoordinateManager;
import ca.mcmaster.se2aa4.island.team45.map.POIManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FlyStage1 extends Stage {
    private final Logger logger = LogManager.getLogger();
    private LandFinder landFinder = new LandFinder();

    public String makeDecision(DirectionManager direction, BatteryManager battery, PreviousResult pResult, PreviousDecision pDecision, StageManager sm, POIManager poiManager, CoordinateManager cm) {
        logger.info("Flying");
        if(pResult.getFound().equals(null)) {
            pDecision.setPrevAction("echo");
            pDecision.setPrevHeading(direction.getDirection());
            logger.info("Echoing forward...");
            return FlightCommands.getInstance().echo(direction.getDirection());
        } else {
            if (pResult.getFound().equals("OUT_OF_RANGE")) {
                if(direction.getDirection().equals("N")) {
                    if (cm.getCoordinates()[0] == (poiManager.getIslandEdge("West")[0] -1) || cm.getCoordinates()[0] == (poiManager.getIslandEdge("West")[0] - 2)) {
                        return FlightCommands.getInstance().stop();
                    }
                    pDecision.setPrevAction("heading");
                    pDecision.setPrevHeading(direction.getLeft());

                    return FlightCommands.getInstance().heading(direction.getLeft());
                } else {
                    if (cm.getCoordinates()[0] == (poiManager.getIslandEdge("West")[0] -1) || cm.getCoordinates()[0] == (poiManager.getIslandEdge("West")[0] - 2)) {
                        return FlightCommands.getInstance().stop();
                    }
                    pDecision.setPrevAction("heading");
                    pDecision.setPrevHeading(direction.getRight());

                    return FlightCommands.getInstance().heading(direction.getRight());
                }
            } else if (landFinder.wasLand(pResult)) {
                sm.setStage(new POIStage1());
                pDecision.setPrevAction("fly");

                return FlightCommands.getInstance().fly();
            }
            pDecision.setPrevAction("fly");

            return FlightCommands.getInstance().fly();
        }
    }
}