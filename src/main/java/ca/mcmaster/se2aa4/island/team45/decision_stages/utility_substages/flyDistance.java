package ca.mcmaster.se2aa4.island.team45.decision_stages.utility_substages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.team45.decision_stages.Stage;
import ca.mcmaster.se2aa4.island.team45.decision_stages.StageManager;
import ca.mcmaster.se2aa4.island.team45.drone.FlightCommands;
import ca.mcmaster.se2aa4.island.team45.drone.PreviousState;
import ca.mcmaster.se2aa4.island.team45.drone.battery.BatteryManager;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.map.POIManager;
import ca.mcmaster.se2aa4.island.team45.map.coordinates.CoordinateManager;

public class flyDistance implements Stage {
    private final Logger logger = LogManager.getLogger();
    private int distanceToTravel;
    private Stage returnStage;

    public flyDistance(int flyDistance, Stage returnStage) {
        this.distanceToTravel = flyDistance;
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
            logger.info("** Flying forward {} squares **", distanceToTravel);
            if (distanceToTravel <= 0) {
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
