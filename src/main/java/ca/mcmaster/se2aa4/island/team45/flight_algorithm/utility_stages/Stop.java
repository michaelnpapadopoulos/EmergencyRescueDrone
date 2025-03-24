package ca.mcmaster.se2aa4.island.team45.flight_algorithm.utility_stages;

import ca.mcmaster.se2aa4.island.team45.drone.commands.CommandCenter;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.flight_algorithm.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Stop implements Stage {
    private final Logger logger = LogManager.getLogger();

    @Override
    public String makeDecision(DirectionManager directionManager, CommandCenter commandCenter, AlgorithmManager algorithmManager) {
        logger.info("** Stopping drone **");
        return commandCenter.makeAction("stop");
    }
}
