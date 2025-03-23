package ca.mcmaster.se2aa4.island.team45.decision_stages.finding_island;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.team45.decision_stages.Stage;
import ca.mcmaster.se2aa4.island.team45.decision_stages.StageManager;
import ca.mcmaster.se2aa4.island.team45.drone.commands.CommandCenter;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;
public class FirstEcho implements Stage {
    private final Logger logger = LogManager.getLogger();

    public String makeDecision(DirectionManager directionManager, CommandCenter commandCenter, StageManager stageManager) { // Echo forward once to possible find the edge of the island
            logger.info("** First Echo **");
            String echoDirection = directionManager.getDirection().stringForward();
            return commandCenter.makeAction("echo", echoDirection);
        } 
}
