package ca.mcmaster.se2aa4.island.team45.drone.drone_observers;

import ca.mcmaster.se2aa4.island.team45.drone.DroneStatus;
import ca.mcmaster.se2aa4.island.team45.drone.commands.CommandCenter;
import ca.mcmaster.se2aa4.island.team45.drone.commands.PreviousResult;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;

public class CoordinateObserver implements StatusObserver {
    @Override
    public void updateStatus(DroneStatus droneStatus, PreviousResult previousResult) {
        DirectionManager directionManager = droneStatus.getDirectionManager();
        CommandCenter commandCenter = droneStatus.getCommandCenter();

        droneStatus.getCoordinateManager().adjustCoords(directionManager, commandCenter);
    }
}
