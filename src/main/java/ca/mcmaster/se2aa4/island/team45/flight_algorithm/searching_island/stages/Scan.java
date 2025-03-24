package ca.mcmaster.se2aa4.island.team45.flight_algorithm.searching_island.stages;

import ca.mcmaster.se2aa4.island.team45.drone.commands.CommandCenter;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.flight_algorithm.*;

public class Scan implements Stage {
    @Override
    public String makeDecision(DirectionManager directionManager, CommandCenter commandCenter, AlgorithmManager algorithmManager) {
        if (commandCenter.wasPrevAction("fly")) {
            return commandCenter.makeAction("scan");
        } else {
            return commandCenter.makeAction("fly");
        }
    }
    
}
