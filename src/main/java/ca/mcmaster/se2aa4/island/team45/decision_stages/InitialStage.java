package ca.mcmaster.se2aa4.island.team45.decision_stages;

import ca.mcmaster.se2aa4.island.team45.drone.BatteryManager;
import ca.mcmaster.se2aa4.island.team45.drone.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.drone.FlightCommands;
import ca.mcmaster.se2aa4.island.team45.drone.PreviousResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InitialStage extends Stage { // The initial stage of the mission, drone has just been spawned into corner of map
    private final Logger logger = LogManager.getLogger();
    private int forwardCount = 0;

    public String makeDecision(DirectionManager direction, BatteryManager battery, PreviousResult pResult, PreviousDecision pDecision, StageManager sm) {

        if (pDecision.getPrevAction() == null) {
            pDecision.setPrevAction("fly");
            return FlightCommands.getInstance().fly();
        } else if (forwardCount == 25) {
            pDecision.setPrevAction("heading");
            direction.turnRight();
            sm.setStage(new FindStage());
            return FlightCommands.getInstance().heading(direction.getDirection());
        }
        
        if (pDecision.getPrevAction().equals("fly")) {
            forwardCount++;
            pDecision.setPrevAction("scan");
            return FlightCommands.getInstance().scan();
        } else {
            pDecision.setPrevAction("fly");
            return FlightCommands.getInstance().fly();
        }
    }
}
