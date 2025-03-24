package ca.mcmaster.se2aa4.island.team45.flight_algorithm.finding_island.stages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.team45.drone.commands.CommandCenter;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.flight_algorithm.Stage;
import ca.mcmaster.se2aa4.island.team45.flight_algorithm.AlgorithmManager;

public class DirectionalSweep implements Stage {
    private final Logger logger = LogManager.getLogger();
    String directionToEcho;

    /**************************************************************************
     * Direction sweep constructor
     * 
     * @param directionToEcho a direction (N,E,S,W) to echo in
    **************************************************************************/
    public DirectionalSweep(String directionToEcho) {
        this.directionToEcho = directionToEcho;
    }

    /**************************************************************************
     * Decides whether to fly or echo
     * 
     * @param directionManager the drones direction manager object
     * @param commandCenter the drones command center object
     * @param algorithmManager the programs algorithm manager
    **************************************************************************/
    @Override
    public String makeDecision(DirectionManager directionManager, CommandCenter commandCenter, AlgorithmManager algorithmManager) {
        logger.info("** Echoing in {} direction to find edge **", directionToEcho);

        if (commandCenter.wasPrevAction("fly")) {
            return commandCenter.makeAction("echo", directionToEcho);
        } else {
            return commandCenter.makeAction("fly");
        }
    }
}

