package ca.mcmaster.se2aa4.island.team45.decision_stages;

import ca.mcmaster.se2aa4.island.team45.drone.BatteryManager;
import ca.mcmaster.se2aa4.island.team45.drone.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.drone.FlightCommands;
import ca.mcmaster.se2aa4.island.team45.drone.PreviousResult;
import ca.mcmaster.se2aa4.island.team45.map.CoordinateManager;
import ca.mcmaster.se2aa4.island.team45.map.POIManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShiftStage extends Stage {
    private final Logger logger = LogManager.getLogger();

    public String makeDecision(DirectionManager direction, BatteryManager battery, PreviousResult pResult, PreviousDecision pDecision, StageManager sm, POIManager poiManager, CoordinateManager cm) {
        logger.info("Turning");
        if(cm.getCoordinates()[1] == (poiManager.getIslandEdge("North")[1]-1)) {
            if (pDecision.getPrevHeading().equals("E")) {
                pDecision.setPrevAction("heading");
                pDecision.setPrevHeading(direction.getRight());

                return FlightCommands.getInstance().heading(direction.getRight());
            } else {
                sm.setStage(new POIStage2());
                pDecision.setPrevAction("fly");

                return FlightCommands.getInstance().fly();
            }
        } else if (cm.getCoordinates()[1] == (poiManager.getIslandEdge("South")[1]+1)) {
            if (pDecision.getPrevHeading().equals("E")) {
                pDecision.setPrevAction("heading");
                pDecision.setPrevHeading(direction.getLeft());

                return FlightCommands.getInstance().heading(direction.getLeft());
            } else {
                sm.setStage(new POIStage2());
                pDecision.setPrevAction("fly");

                return FlightCommands.getInstance().fly();
            }
        } else {
            if (pDecision.getPrevHeading().equals("N")) {
                pDecision.setPrevAction("heading");
                pDecision.setPrevHeading(direction.getRight());

                return FlightCommands.getInstance().heading(direction.getRight());
            } else {
                pDecision.setPrevAction("heading");
                pDecision.setPrevHeading(direction.getLeft());

                return FlightCommands.getInstance().heading(direction.getLeft());
            }
        }      
    }
}
