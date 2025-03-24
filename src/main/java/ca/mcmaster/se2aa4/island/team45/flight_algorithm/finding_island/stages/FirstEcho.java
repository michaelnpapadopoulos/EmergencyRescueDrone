package ca.mcmaster.se2aa4.island.team45.flight_algorithm.finding_island.stages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.team45.drone.commands.CommandCenter;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.flight_algorithm.Stage;
import ca.mcmaster.se2aa4.island.team45.flight_algorithm.AlgorithmManager;

public class FirstEcho implements Stage {
    private final Logger logger = LogManager.getLogger();

    /**************************************************************************
     * Echos once
     * 
     * @param directionManager the drones direction manager object
     * @param commandCenter the drones command center object
     * @param algorithmManager the programs algorithm manager
    **************************************************************************/
    public String makeDecision(DirectionManager directionManager, CommandCenter commandCenter, AlgorithmManager algorithmManager) { // Echo forward once to possible find the edge of the island
            logger.info("** First Echo **");
            String echoDirection = directionManager.getDirection().toString();
            return commandCenter.makeAction("echo", echoDirection);
        } 
}
