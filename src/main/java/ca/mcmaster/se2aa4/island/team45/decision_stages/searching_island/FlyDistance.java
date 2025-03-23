package ca.mcmaster.se2aa4.island.team45.decision_stages.searching_island;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.team45.decision_stages.Stage;
import ca.mcmaster.se2aa4.island.team45.decision_stages.StageManager;
import ca.mcmaster.se2aa4.island.team45.drone.commands.CommandCenter;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;

public class FlyDistance implements Stage {
    private final Logger logger = LogManager.getLogger();
    private boolean hasEchoed = false; // Distance to travel yet to be initialized, will using result from previous stage's echo

    @Override
    public String makeDecision(DirectionManager directionManager, CommandCenter commandCenter, StageManager stageManager) {
        logger.info("** Flying forward to cross land gap **");
        if (!hasEchoed) {
            hasEchoed = true;
            return commandCenter.makeAction("echo", directionManager.getDirection().stringForward());
        }
        
        return commandCenter.makeAction("fly");
    }
}
