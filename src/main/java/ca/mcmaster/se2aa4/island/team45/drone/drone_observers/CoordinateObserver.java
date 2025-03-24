package ca.mcmaster.se2aa4.island.team45.drone.drone_observers;

import ca.mcmaster.se2aa4.island.team45.drone.*;
import ca.mcmaster.se2aa4.island.team45.drone.commands.CommandCenter;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.map.interest_points.POIManager;

public class CoordinateObserver implements StatusObserver {
    @Override
    public void updateStatus(DroneStatus droneStatus, PreviousResult previousResult, POIManager poiManager) {
        DirectionManager directionManager = droneStatus.getDirectionManager();
        CommandCenter commandCenter = droneStatus.getCommandCenter();

        droneStatus.getCoordinateManager().adjustCoords(directionManager, commandCenter);
    }
}
