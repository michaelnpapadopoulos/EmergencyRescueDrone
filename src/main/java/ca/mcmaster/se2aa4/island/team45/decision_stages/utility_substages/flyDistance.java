package ca.mcmaster.se2aa4.island.team45.decision_stages.utility_substages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.lang.Math;

import ca.mcmaster.se2aa4.island.team45.map.coordinates.Coordinate;
import ca.mcmaster.se2aa4.island.team45.decision_stages.Stage;
import ca.mcmaster.se2aa4.island.team45.decision_stages.StageManager;
import ca.mcmaster.se2aa4.island.team45.drone.battery.BatteryManager;
import ca.mcmaster.se2aa4.island.team45.drone.commands.FlightCommands;
import ca.mcmaster.se2aa4.island.team45.drone.commands.PreviousState;
import ca.mcmaster.se2aa4.island.team45.drone.direction.Direction;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.map.coordinates.CoordinateManager;
import ca.mcmaster.se2aa4.island.team45.map.interest_points.POIManager;

public class FlyDistance implements Stage {
    private final Logger logger = LogManager.getLogger();
    private int distanceToTravel = -1; // Distance to travel yet to be initialized, will using result from previous stage's echo
    private Stage returnStage;

    public FlyDistance(Stage returnStage) {
        this.returnStage = returnStage;
    }

    public String makeDecision(
        DirectionManager direction, 
        BatteryManager battery, 
        PreviousState pState, 
        StageManager sm, 
        POIManager poiManager, 
        CoordinateManager cm
        ) {
            logger.info("** Flying forward to cross land gap **");
            if (distanceToTravel == -1) {
                distanceToTravel = pState.getRange();
            }

            if (pState.getPrevAction().equals("echo") && pState.getFound().equals("OUT_OF_RANGE")) { // Echo out of range, fly to edge of island
                Direction facingDirection = direction.getDirection();
                Coordinate facingEdge = poiManager.getIslandEdge(facingDirection.getFullDirectionString(facingDirection.stringForward()));
                
                if (facingDirection.stringForward().equals("N") || facingDirection.stringForward().equals("S")) {
                    distanceToTravel = Math.abs(cm.getCoordinates().getY().getCoordVal() - facingEdge.getY().getCoordVal());
                } else {
                    distanceToTravel = Math.abs(cm.getCoordinates().getX().getCoordVal() - facingEdge.getX().getCoordVal());
                }
            }
           

            if (distanceToTravel <= 1) {
                sm.setStage(returnStage);
                pState.setPrevAction("fly");
                return FlightCommands.getInstance().fly();
            } else {
                distanceToTravel--;
                pState.setPrevAction("fly");
                return FlightCommands.getInstance().fly();
            }
        }
}
