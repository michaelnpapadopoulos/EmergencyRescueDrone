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

public class DirectionalSweep implements Stage {
    private final Logger logger = LogManager.getLogger();
    String directionToEcho;

    public DirectionalSweep(String directionToEcho) {
        this.directionToEcho = directionToEcho;
    }

    public String makeDecision(
        DirectionManager direction, 
        BatteryManager battery, 
        PreviousState previousState, 
        StageManager stageManager, 
        POIManager poiManager, 
        CoordinateManager coordinateManager
        ) {
            logger.info("** Echoing in {} direction to find edge **", directionToEcho);

            if (previousState.getPrevAction().equals("fly")) {
                previousState.setPrevAction("echo");
                previousState.setPrevHeading(directionToEcho);

                return FlightCommands.getInstance().echo(directionToEcho);
            } else {
                previousState.setPrevAction("fly");
                return FlightCommands.getInstance().fly();
            }
    }
}

