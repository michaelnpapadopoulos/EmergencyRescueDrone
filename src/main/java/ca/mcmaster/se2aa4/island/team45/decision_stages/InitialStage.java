package ca.mcmaster.se2aa4.island.team45.decision_stages;

import ca.mcmaster.se2aa4.island.team45.drone.BatteryManager;
import ca.mcmaster.se2aa4.island.team45.drone.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.drone.FlightCommands;
import ca.mcmaster.se2aa4.island.team45.drone.PreviousResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InitialStage extends Stage { // The initial stage of the mission, find first edge of island
    private final Logger logger = LogManager.getLogger();

    public String makeDecision(DirectionManager direction, BatteryManager battery, PreviousResult pResult, PreviousDecision pDecision, StageManager sm) {
        logger.info("Making initial stage decision...");
        if (pDecision.getPrevAction() == null || pDecision.getPrevAction().equals("fly")) {
            pDecision.setPrevAction("echo");
            pDecision.setPrevHeading(direction.getRight());
            return FlightCommands.getInstance().echo(direction.getRight());
        } else if (pResult.getFound() != null && pResult.getFound().equals("GROUND")) {
            logger.info("Found edge of island");
            pDecision.setPrevAction("scan");
            sm.setStage(new PerimeterStage());
            return FlightCommands.getInstance().scan();
        } else {
            pDecision.setPrevAction("fly");
            return FlightCommands.getInstance().fly();
        }
    }
}
