package ca.mcmaster.se2aa4.island.team45.decision_stages;

import ca.mcmaster.se2aa4.island.team45.drone.BatteryManager;
import ca.mcmaster.se2aa4.island.team45.drone.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.drone.FlightCommands;
import ca.mcmaster.se2aa4.island.team45.drone.PreviousResult;
import ca.mcmaster.se2aa4.island.team45.map.CoordinateManager;
import ca.mcmaster.se2aa4.island.team45.map.POIManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class POIStage2 extends Stage { 
    private final Logger logger = LogManager.getLogger();

    public String makeDecision(DirectionManager direction, BatteryManager battery, PreviousResult pResult, PreviousDecision pDecision, StageManager sm, POIManager poiManager, CoordinateManager cm) {
        logger.info("Searching");
        if(cm.getCoordinates()[1] == (poiManager.getIslandEdge("South")[1]+1)) {
            if (cm.getCoordinates()[0] == poiManager.getIslandEdge("West")[0] || cm.getCoordinates()[0] == (poiManager.getIslandEdge("West")[0] - 1)) {

                return FlightCommands.getInstance().stop();
            } else if(!pDecision.getPrevAction().equals("heading")) {
                pDecision.setPrevAction("heading");
                pDecision.setPrevHeading(direction.getLeft());

                return FlightCommands.getInstance().heading(direction.getLeft());
            }
        } else if (cm.getCoordinates()[1] == (poiManager.getIslandEdge("South")[1]+2)) {
            pDecision.setPrevAction("heading");
            pDecision.setPrevHeading(direction.getLeft());

            return FlightCommands.getInstance().heading(direction.getLeft());
        }

        if(cm.getCoordinates()[1] == (poiManager.getIslandEdge("North")[1]-1)) {
            if (cm.getCoordinates()[0] == (poiManager.getIslandEdge("East")[0] +1) || cm.getCoordinates()[0] == (poiManager.getIslandEdge("East")[0] + 2)) {

                return FlightCommands.getInstance().stop();
            } else if(!pDecision.getPrevAction().equals("heading")) {
                pDecision.setPrevAction("heading");
                pDecision.setPrevHeading(direction.getRight());

                return FlightCommands.getInstance().heading(direction.getRight());
            }
        } else if (cm.getCoordinates()[1] == (poiManager.getIslandEdge("North")[1]-2)) {
            pDecision.setPrevAction("heading");
            pDecision.setPrevHeading(direction.getRight());

            return FlightCommands.getInstance().heading(direction.getRight());
        }

        if(!pDecision.getPrevAction().equals("fly")) {
            pDecision.setPrevAction("fly");

            return FlightCommands.getInstance().fly();
        } else {
            pDecision.setPrevAction("scan");

            return FlightCommands.getInstance().scan();        
        }
    }
}
