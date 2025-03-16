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
        logger.info("Making initial stage decision...");
        if (pDecision.getPrevAction() == null) {
            pDecision.setPrevAction("scan");
            return FlightCommands.getInstance().scan();
        } else if (forwardCount == 25) {
            if (pDecision.getPrevAction().equals("scan")) {
                pDecision.setPrevAction("heading");
                pDecision.setPrevHeading(direction.getRight());
                sm.setStage(new FindStage()); // Change mission stage to FindStage
                return FlightCommands.getInstance().heading(direction.getRight());
            } else {
                pDecision.setPrevAction("scan");
                return FlightCommands.getInstance().scan();
            }            
        }
        
        if (pDecision.getPrevAction().equals("fly")) {
            pDecision.setPrevAction("scan");
            return FlightCommands.getInstance().scan();
        } else {
            forwardCount++;
            pDecision.setPrevAction("fly");
            return FlightCommands.getInstance().fly();
        }
    }
}
