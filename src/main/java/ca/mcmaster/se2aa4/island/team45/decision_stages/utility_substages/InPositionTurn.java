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

public class InPositionTurn implements Stage {
    private final Logger logger = LogManager.getLogger();
    private int turnCount = 0;
    private String turnDirection;
    private Stage returnStage;

    public InPositionTurn(String turnDirection, Stage returnStage) {
        this.turnDirection = turnDirection;
        this.returnStage = returnStage;
    }

    private String inPositionRight(DirectionManager direction, PreviousState pState, StageManager sm) {
        logger.info("** Making an in position right turn **");
        String headingDir;

        if (turnCount == 4) { // Final right turn to face the inital direction
            pState.setPrevAction("heading");
            pState.setPrevHeading(direction.getDirection().stringRight());
            sm.setStage(returnStage);
            return FlightCommands.getInstance().heading(direction.getDirection().stringRight());
        }

        if (turnCount == 0) {
            turnCount++;
            headingDir = direction.getDirection().stringRight();
            
        } else if (turnCount == 1) { // Offsets plane by 1 square to allow for an in position right turn
            turnCount++;
            pState.setPrevAction("fly");
            return FlightCommands.getInstance().fly();

        } else if (turnCount == 2) {
            turnCount++;
            headingDir = direction.getDirection().stringRight();

        } else {
            turnCount++;
            headingDir = direction.getDirection().stringRight();
        }

        pState.setPrevAction("heading");
        pState.setPrevHeading(headingDir);
        
        return FlightCommands.getInstance().heading(headingDir);
    }

    private String inPositionLeft(DirectionManager direction, PreviousState pState, StageManager sm) {
        logger.info("** Making an in position left turn **");
        String headingDir;

        if (turnCount == 4) { // Final left turn to face the inital direction
            pState.setPrevAction("heading");
            pState.setPrevHeading(direction.getDirection().stringLeft());
            sm.setStage(returnStage);
            return FlightCommands.getInstance().heading(direction.getDirection().stringLeft());
        }

        if (turnCount == 0) {
            turnCount++;
            headingDir = direction.getDirection().stringLeft();
            
        } else if (turnCount == 1) { // Offsets plane by 1 square to allow for an in position left turn
            turnCount++;
            pState.setPrevAction("fly");
            return FlightCommands.getInstance().fly();

        } else if (turnCount == 2) {
            turnCount++;
            headingDir = direction.getDirection().stringLeft();

        } else {
            turnCount++;
            headingDir = direction.getDirection().stringLeft();
        }

        pState.setPrevAction("heading");
        pState.setPrevHeading(headingDir);
        
        return FlightCommands.getInstance().heading(headingDir);
    }

    @Override
    public String makeDecision(
        DirectionManager direction, 
        BatteryManager battery, 
        PreviousState previousState, 
        StageManager stageManager, 
        POIManager poiManager, 
        CoordinateManager coordinateManager
        ) {
            if (turnDirection.equals("right")) {
                return inPositionRight(direction, previousState, stageManager);
            } else if (turnDirection.equals("left")) {
                return inPositionLeft(direction, previousState, stageManager);
            } else {
                logger.error("** Invalid turn direction **");
                return null;
            }
    }
}
