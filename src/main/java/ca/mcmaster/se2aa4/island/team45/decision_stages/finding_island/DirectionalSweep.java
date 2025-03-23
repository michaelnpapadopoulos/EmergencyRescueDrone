package ca.mcmaster.se2aa4.island.team45.decision_stages.finding_island;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.team45.decision_stages.Stage;
import ca.mcmaster.se2aa4.island.team45.decision_stages.StageManager;
import ca.mcmaster.se2aa4.island.team45.drone.commands.CommandCenter;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionEnum;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;

public class DirectionalSweep implements Stage {
    private final Logger logger = LogManager.getLogger();
    DirectionEnum directionToEcho;

    public DirectionalSweep(DirectionEnum directionToEcho) {
        this.directionToEcho = directionToEcho;
    }

    @Override
    public String makeDecision(DirectionManager directionManager, CommandCenter commandCenter, StageManager stageManager) {
        logger.info("** Echoing in {} direction to find edge **", directionToEcho);

        if (commandCenter.getPrevAction().equals("fly")) {
            return commandCenter.makeAction("echo", directionToEcho.getLongDir());
        } else {
            return commandCenter.makeAction("fly");
        }
    }
}

