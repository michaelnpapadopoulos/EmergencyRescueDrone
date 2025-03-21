package ca.mcmaster.se2aa4.island.team45.decision_stages.utility_substages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Removed PreviousDecision import since we're using PreviousState
import ca.mcmaster.se2aa4.island.team45.decision_stages.Stage;
import ca.mcmaster.se2aa4.island.team45.decision_stages.StageManager;
import ca.mcmaster.se2aa4.island.team45.drone.FlightCommands;
import ca.mcmaster.se2aa4.island.team45.drone.PreviousState;
import ca.mcmaster.se2aa4.island.team45.drone.battery.BatteryManager;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.map.POIManager;
import ca.mcmaster.se2aa4.island.team45.map.coordinates.CoordinateManager;

public class UTurn implements Stage {
    private final Logger logger = LogManager.getLogger();
    private boolean turned = false;
    private String turnDirection;
    private Stage returnStage;

    public UTurn(String turnDirection, Stage returnStage) {
        this.turnDirection = turnDirection;
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
            if (turnDirection.equals("right")) {
                return uTurnRight(direction, pState, sm);
            } else {
                return uTurnLeft(direction, pState, sm);
                
            } 
        }

    private String uTurnRight(DirectionManager direction, PreviousState pState, StageManager sm) {
        logger.info("** Making a right U-turn **");
       
        if (turned == true) {
            sm.setStage(returnStage);
            pState.setPrevUTurn("right");
        }

        this.turned = true;

        pState.setPrevAction("heading");
        pState.setPrevHeading(direction.getDirection().stringRight());
        return FlightCommands.getInstance().heading(direction.getDirection().stringRight());
    }

    private String uTurnLeft(DirectionManager direction, PreviousState pState, StageManager sm) {
        logger.info("** Making a left U-turn **");

        if (turned == true) {
            sm.setStage(returnStage);
            pState.setPrevUTurn("left");
        }

        this.turned = true;

        pState.setPrevAction("heading");
        pState.setPrevHeading(direction.getDirection().stringLeft());
        return FlightCommands.getInstance().heading(direction.getDirection().stringLeft());
    }
}
