package ca.mcmaster.se2aa4.island.team45.decision_stages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.team45.drone.BatteryManager;
import ca.mcmaster.se2aa4.island.team45.drone.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.drone.FlightCommands;
import ca.mcmaster.se2aa4.island.team45.drone.PreviousResult;
import ca.mcmaster.se2aa4.island.team45.map.CoordinateManager;
import ca.mcmaster.se2aa4.island.team45.map.POIManager;

public class PerimeterStage extends Stage { // Locate and mark the edges of the perimeter
    private final Logger logger = LogManager.getLogger();
    
    public String makeDecision(DirectionManager direction, BatteryManager battery, PreviousResult pResult, PreviousDecision pDecision, StageManager sm, POIManager poiManager, CoordinateManager cm) {
        logger.info("Making perimeter stage decision...");
        if (pDecision.getPrevAction().equals("fly") || pDecision.getPrevAction().equals("heading") || pDecision.getPrevAction().equals("scan")) {

            if (pDecision.getPrevAction().equals("scan")) {
                pDecision.setPrevAction("echo");
                pDecision.setPrevHeading(direction.getRight());
                logger.info("Echoing right...");
                return FlightCommands.getInstance().echo(direction.getRight());
            } else {
                pDecision.setPrevAction("scan");
                return FlightCommands.getInstance().scan();
            }
            

        } else if (pResult.getFound() != null && pResult.getFound().equals("OUT_OF_RANGE") && poiManager.getIslandEdge("East") == null && poiManager.getIslandEdge("West") != null) { // Echos right until East edge is detected then turns right
            logger.info("Found East edge of island");
            pDecision.setPrevAction("heading");
            pDecision.setPrevHeading(direction.getRight());

            poiManager.addIslandEdge("East", new int[] {cm.getX()-1, -1});
            return FlightCommands.getInstance().heading(direction.getRight());
        } else if (pResult.getFound() != null && pResult.getFound().equals("GROUND") && poiManager.getIslandEdge("North") == null && poiManager.getIslandEdge("East") != null) { // Echos right until North edge is detected, continues straight
            logger.info("Found North edge of island");
            pDecision.setPrevAction("fly");

            poiManager.addIslandEdge("North", new int[] {-1, cm.getY()});
            return FlightCommands.getInstance().fly();
        } else if (pResult.getFound() != null && pResult.getFound().equals("OUT_OF_RANGE") && poiManager.getIslandEdge("South") == null && poiManager.getIslandEdge("North") != null) { // Echos right until South edge is detected, scans and initiates next stage
            logger.info("Found South edge of island");
            pDecision.setPrevAction("scan");

            poiManager.addIslandEdge("South", new int[] {-1, cm.getY()-1});
            sm.setStage(new FlyStage1());
            return FlightCommands.getInstance().scan();
        } else {
            pDecision.setPrevAction("fly");
            return FlightCommands.getInstance().fly();
        }
    }
}
