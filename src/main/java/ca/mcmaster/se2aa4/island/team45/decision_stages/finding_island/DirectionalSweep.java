package ca.mcmaster.se2aa4.island.team45.decision_stages.finding_island;

import ca.mcmaster.se2aa4.island.team45.decision_stages.PreviousDecision;
import ca.mcmaster.se2aa4.island.team45.decision_stages.Stage;
import ca.mcmaster.se2aa4.island.team45.drone.FlightCommands;
import ca.mcmaster.se2aa4.island.team45.drone.BatteryManager;
import ca.mcmaster.se2aa4.island.team45.drone.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.drone.PreviousResult;
import ca.mcmaster.se2aa4.island.team45.map.POIManager;
import ca.mcmaster.se2aa4.island.team45.map.coordinates.CoordinateManager;
import ca.mcmaster.se2aa4.island.team45.decision_stages.StageManager;
import ca.mcmaster.se2aa4.island.team45.decision_stages.utility_substages.InPositionTurn;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DirectionalSweep extends Stage {
    private final Logger logger = LogManager.getLogger();
    String directionToEcho;

    public DirectionalSweep(String directionToEcho) {
        this.directionToEcho = directionToEcho;
    }

    public String makeDecision(
        DirectionManager direction, 
        BatteryManager battery, 
        PreviousResult pResult, 
        PreviousDecision pDecision, 
        StageManager sm, 
        POIManager poiManager, 
        CoordinateManager cm
        ) {
            logger.info("** Echoing in {} direction to find edge **", directionToEcho);

            if (pResult.getFound() != null && pResult.getFound().equals("OUT_OF_BOUNDS")) {
                logger.info("** Found the edge of the island **");
                pDecision.setPrevAction("heading");
                pDecision.setPrevHeading(directionToEcho);
                sm.setStage(new DirectionalSweep());
                poiManager.addIslandEdge(direction, cm.getRearCoordinate(direction));

                return FlightCommands.getInstance().heading(directionToEcho);
            }

            if (pDecision.getPrevAction().equals("fly")) {
                pDecision.setPrevAction("echo");
                pDecision.setPrevHeading(directionToEcho);

                return FlightCommands.getInstance().echo(directionToEcho);
            } else {
                pDecision.setPrevAction("fly");
                return FlightCommands.getInstance().fly();
            }
    }
    
}

