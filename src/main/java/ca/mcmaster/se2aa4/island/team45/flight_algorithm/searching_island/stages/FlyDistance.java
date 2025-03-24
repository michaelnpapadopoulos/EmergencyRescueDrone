package ca.mcmaster.se2aa4.island.team45.flight_algorithm.searching_island.stages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.team45.drone.commands.CommandCenter;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.flight_algorithm.*;

public class FlyDistance implements Stage {
    private final Logger logger = LogManager.getLogger();
    private boolean hasEchoed = false; // Distance to travel yet to be initialized, will using result from previous stage's echo

    /**************************************************************************
     * Echos once and then flies forward while over water
     * 
     * @param directionManager the drones direction manager object
     * @param commandCenter the drones command center object
     * @param algorithmManager the programs algorithm manager
    **************************************************************************/
    @Override
    public String makeDecision(DirectionManager directionManager, CommandCenter commandCenter, AlgorithmManager algorithmManager) {
        logger.info("** Flying forward to cross land gap **");
        if (!hasEchoed) {
            hasEchoed = true;
            return commandCenter.makeAction("echo", directionManager.getDirection().toString());
        }
        
        return commandCenter.makeAction("fly");
    }
}
