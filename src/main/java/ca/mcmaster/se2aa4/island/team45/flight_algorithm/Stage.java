package ca.mcmaster.se2aa4.island.team45.flight_algorithm;

import ca.mcmaster.se2aa4.island.team45.drone.commands.CommandCenter;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;

public interface Stage {
    String makeDecision(DirectionManager directionManager, CommandCenter commandCenter, AlgorithmManager algorithmManager);
}