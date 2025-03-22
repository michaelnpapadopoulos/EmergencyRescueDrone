package ca.mcmaster.se2aa4.island.team45.decision_stages.finding_island;

import ca.mcmaster.se2aa4.island.team45.decision_stages.Stage;
import ca.mcmaster.se2aa4.island.team45.drone.battery.BatteryManager;
import ca.mcmaster.se2aa4.island.team45.drone.commands.FlightCommands;
import ca.mcmaster.se2aa4.island.team45.drone.commands.PreviousState;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.map.coordinates.CoordinateManager;
import ca.mcmaster.se2aa4.island.team45.map.interest_points.POIManager;
import ca.mcmaster.se2aa4.island.team45.decision_stages.StageManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EchoSweep implements Stage {
    private final Logger logger = LogManager.getLogger();
    boolean echoedRight = false;
    boolean echoedLeft = false;

    public String makeDecision(
        DirectionManager direction, 
        BatteryManager battery, 
        PreviousState previousState, 
        StageManager stageManager, 
        POIManager poiManager, 
        CoordinateManager coordinateManager
        ) { // Echos left, forward, then right to find the edge of the island
            String echoDirection;
            if (echoedRight == false) { // Echo right
                echoedRight = true;
                echoDirection = direction.getDirection().stringRight();
                
            } else if (echoedLeft == false) { // Echo left
                echoedLeft = true;
                echoDirection = direction.getDirection().stringLeft();

            } else {
                logger.info("** Flying forward **");
                this.echoedRight = false; this.echoedLeft = false;

                previousState.setPrevAction("fly");
                return FlightCommands.getInstance().fly();
            }

            logger.info("** Echoing " + echoDirection + " **");
            previousState.setPrevAction("echo");
            previousState.setPrevHeading(echoDirection);
            
            return FlightCommands.getInstance().echo(echoDirection);
    }
    
}
