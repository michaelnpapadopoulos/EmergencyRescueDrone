package ca.mcmaster.se2aa4.island.team45.decision_stages;

import ca.mcmaster.se2aa4.island.team45.drone.PreviousResult;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LandFinder { // The initial stage of the mission, find first edge of island
    private final Logger logger = LogManager.getLogger();

    public boolean wasLand (PreviousResult pResult) {
        logger.info("Checking for Land");
        if (pResult.getLand() || (pResult.getFound().equals("GROUND") && pResult.getRange().equals("1"))) {
            return true;  
        } else {
            return false;
        } 
    }    
}
