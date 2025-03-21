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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EchoSweep extends Stage {
    private final Logger logger = LogManager.getLogger();
    boolean echoedRight = false;
    boolean echoedLeft = false;
    String relativeLastEchoed;

    public String makeDecision(
        DirectionManager direction, 
        BatteryManager battery, 
        PreviousResult pResult, 
        PreviousDecision pDecision, 
        StageManager sm, 
        POIManager poiManager, 
        CoordinateManager cm
        ) { // Echos left, forward, then right to find the edge of the island
            logger.info("** Echoing in left and right directions to find island edge **");
            if (pResult.getFound() != null && pResult.getFound().equals("GROUND")) { // Turns in the direction that the island was found in
                logger.info("** Found the edge of the island **");

                if (pDecision.getPrevHeading().equals(direction.getDirection())) {
                    pDecision.setPrevAction("heading");
                    pDecision.setPrevHeading(direction.getLeft());
                    sm.setStage(new FindEdge("right")); // Sets the next stage to FindEdge if forward echo has found the edge of the island
                    return FlightCommands.getInstance().heading(direction.getLeft());
                }

                poiManager.addIslandEdge(direction, cm.getCoordinates());
                pDecision.setPrevAction("fly");
                sm.setStage(new FindEdge(relativeLastEchoed));
                return FlightCommands.getInstance().fly();
            } 


            String echoDirection;
            if (echoedRight == false) { // Echo right
                echoedRight = true;
                relativeLastEchoed = "right";
                echoDirection = direction.getRight();
                
            } else if (echoedLeft == false) { // Echo forward
                echoedLeft = true;
                relativeLastEchoed = "left";
                echoDirection = direction.getLeft();

            } else {
                logger.info("** Flying forward **");
                this.echoedRight = false; this.echoedLeft = false;

                pDecision.setPrevAction("fly");
                return FlightCommands.getInstance().fly();
            }

            logger.info("** Echoing " + echoDirection + " **");
            pDecision.setPrevAction("echo");
            pDecision.setPrevHeading(echoDirection);
            
            return FlightCommands.getInstance().echo(echoDirection);
    }
    
}
