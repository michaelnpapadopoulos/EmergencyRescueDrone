package ca.mcmaster.se2aa4.island.team45.drone.drone_observers;

import ca.mcmaster.se2aa4.island.team45.drone.*;
import ca.mcmaster.se2aa4.island.team45.drone.commands.CommandCenter;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.map.interest_points.POIManager;

public class DirectionObserver implements StatusObserver {

    /**************************************************************************
     * Updates the directionManager with the drones new direction
     *
     * @param droneStatus tracks the drones current attributes
     * @param previousResult tracks the JSONObject created from the last action
     * @param poiManager used to update the points of interest found during 
     * scan
    **************************************************************************/
    @Override
    public void updateStatus(DroneStatus droneStatus, PreviousResult previousResult, POIManager poiManager) {
        DirectionManager directionManager = droneStatus.getDirectionManager();
        CommandCenter commandCenter = droneStatus.getCommandCenter();

        if (commandCenter.wasPrevAction("heading")) {
            directionManager.setDirection(commandCenter.getPrevHeading());
        }
    }      
}
