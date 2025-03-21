package ca.mcmaster.se2aa4.island.team45.decision_stages.finding_island;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.team45.decision_stages.Stage;
import ca.mcmaster.se2aa4.island.team45.decision_stages.StageManager;
import ca.mcmaster.se2aa4.island.team45.decision_stages.searching_island.SearchInitial;
import ca.mcmaster.se2aa4.island.team45.decision_stages.utility_substages.UTurn;
import ca.mcmaster.se2aa4.island.team45.drone.FlightCommands;
import ca.mcmaster.se2aa4.island.team45.drone.PreviousState;
import ca.mcmaster.se2aa4.island.team45.drone.battery.BatteryManager;
import ca.mcmaster.se2aa4.island.team45.drone.direction.Direction;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.map.POIManager;
import ca.mcmaster.se2aa4.island.team45.map.coordinates.CoordinateManager;

public class FindEdge implements Stage {
    private final Logger logger = LogManager.getLogger();
    private String directionToEcho;
    private boolean setDirectionToEcho = false;

    public String makeDecision(
        DirectionManager direction, 
        BatteryManager battery, 
        PreviousState previousState, 
        StageManager stageManager, 
        POIManager poiManager, 
        CoordinateManager coordinateManager
        ) {
            if (!setDirectionToEcho) { // Sets direction to echo on the first call
                this.directionToEcho = previousState.getPrevHeading();
                this.setDirectionToEcho = true;
            }

            logger.info("** Echoing in {} direction to find edge **", directionToEcho);
            if (previousState.getFound() != null && previousState.getFound().equals("OUT_OF_RANGE")) {
                logger.info("** Found the edge of the island **");
                previousState.setPrevAction("heading");
                previousState.setPrevHeading(directionToEcho);

                String sweepDirection;
                Direction tempDir = new Direction(directionToEcho);
                if (direction.getDirection().stringRight().equals(directionToEcho)) {
                    sweepDirection = tempDir.stringRight();
                } else {
                    sweepDirection = tempDir.stringLeft();
                }
                stageManager.setStage(new DirectionalSweep(sweepDirection));
                poiManager.addIslandEdge(direction, coordinateManager.getRearCoordinate(direction));

                if (poiManager.numberOfIslandEdgesFound() == 4) {
                    logger.info("** Found all island edges **");
                    poiManager.labelIslandEdges(); // Label island edges and add them to the IslandManager's hashmap
                    previousState.setPrevAction("fly");

                    String turnDirection;
                    if (direction.getDirection().stringRight().equals(directionToEcho)) {
                        turnDirection = "right";
                    } else {
                        turnDirection = "left";
                    }

                    stageManager.setStage(new UTurn(turnDirection, new SearchInitial(this.directionToEcho)));
                    return FlightCommands.getInstance().fly();
                }

                return FlightCommands.getInstance().heading(directionToEcho);
            }

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