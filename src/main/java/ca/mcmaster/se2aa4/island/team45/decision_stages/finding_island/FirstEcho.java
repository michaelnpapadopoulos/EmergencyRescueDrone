package ca.mcmaster.se2aa4.island.team45.decision_stages.finding_island;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.team45.decision_stages.Stage;
import ca.mcmaster.se2aa4.island.team45.decision_stages.StageManager;
import ca.mcmaster.se2aa4.island.team45.drone.FlightCommands;
import ca.mcmaster.se2aa4.island.team45.drone.PreviousState;
import ca.mcmaster.se2aa4.island.team45.drone.battery.BatteryManager;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.map.coordinates.CoordinateManager;
import ca.mcmaster.se2aa4.island.team45.map.interest_points.POIManager;

public class FirstEcho implements Stage {
    private final Logger logger = LogManager.getLogger();

    public String makeDecision(
        DirectionManager direction, 
        BatteryManager battery, 
        PreviousState pState,
        StageManager sm, 
        POIManager poiManager, 
        CoordinateManager cm
        ) { // Echo forward once to possible find the edge of the island
            logger.info("** First Echo **");
            pState.setPrevAction("echo");
            pState.setPrevHeading(direction.getDirection().stringForward());
            sm.setStage(new EchoSweep());
            return FlightCommands.getInstance().echo(direction.getDirection().stringForward());
        } 
}
