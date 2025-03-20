package ca.mcmaster.se2aa4.island.team45.decision_stages.findedge_substages;

import ca.mcmaster.se2aa4.island.team45.decision_stages.FindEdge;
import ca.mcmaster.se2aa4.island.team45.decision_stages.PreviousDecision;
import ca.mcmaster.se2aa4.island.team45.decision_stages.Stage;
import ca.mcmaster.se2aa4.island.team45.drone.FlightCommands;
import ca.mcmaster.se2aa4.island.team45.drone.BatteryManager;
import ca.mcmaster.se2aa4.island.team45.drone.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.drone.PreviousResult;
import ca.mcmaster.se2aa4.island.team45.map.CoordinateManager;
import ca.mcmaster.se2aa4.island.team45.map.POIManager;
import ca.mcmaster.se2aa4.island.team45.decision_stages.StageManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EchoSweep extends Stage {
    private final Logger logger = LogManager.getLogger();
    boolean echoedRight = false;
    boolean echoedForward = false;
    boolean echoedLeft = false;
    String relativeLastEchoed;

    public String makeDecision(
        DirectionManager direction, 
        BatteryManager battery, 
        PreviousResult pResult, 
        PreviousDecision pDecision, 
        StageManager sm, 
        POIManager poiManager, 
        CoordinateManager cm) { // Echos left, forward, then right to find the edge of the island
        logger.info("** Echoing in all directions to find the edge of the island **");
        String echoDirection;
        
        if (pResult.getFound() != null && pResult.getFound().equals("GROUND")) { // Turns in the direction that the island was found in
            logger.info("** Found the edge of the island **");
            logger.info("** Distance to edge of island: {} **", pResult.getRange());
            sm.setStage(new InPositionTurn(relativeLastEchoed, new FindEdge(pResult.getRange() - 1))); // Transition to next stage, set distance to edge of island

            if (pDecision.getPrevHeading().equals(direction.getDirection())) { // Just goes straight if island found forwards
                pDecision.setPrevAction("fly");
                return FlightCommands.getInstance().fly();

            } else { // Turns in the direction that the island was found in
                pDecision.setPrevAction("heading");
                pDecision.setPrevHeading(pDecision.getPrevHeading());
                return FlightCommands.getInstance().heading(pDecision.getPrevHeading());
            }
        } 

        if (echoedRight == false) { // Echo right
            echoedRight = true;
            relativeLastEchoed = "right";
            echoDirection = direction.getRight();
            
        } else if (echoedForward == false) { // Echo forward
            echoedForward = true;
            echoDirection = direction.getDirection();

        } else if (echoedLeft == false) { // Echo left
            echoedLeft = true;
            relativeLastEchoed = "left";
            echoDirection = direction.getLeft();

        } else {
            logger.info("** Flying forward **");
            this.echoedRight = false; this.echoedForward = false; this.echoedLeft = false;

            pDecision.setPrevAction("fly");
            return FlightCommands.getInstance().fly();
        }

        logger.info("** Echoing " + echoDirection + " **");
        pDecision.setPrevAction("echo");
        pDecision.setPrevHeading(echoDirection);
        
        return FlightCommands.getInstance().echo(echoDirection);
    }
    
}
