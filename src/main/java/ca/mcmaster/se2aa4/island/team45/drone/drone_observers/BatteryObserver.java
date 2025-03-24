package ca.mcmaster.se2aa4.island.team45.drone.drone_observers;

import ca.mcmaster.se2aa4.island.team45.drone.*;
import ca.mcmaster.se2aa4.island.team45.map.interest_points.POIManager;

public class BatteryObserver implements StatusObserver {

    /**************************************************************************
     * Updates the battery manager by subtracting the previous cost
     *
     * @param droneStatus tracks the drones current attributes
     * @param previousResult tracks the JSONObject created from the last action
     * @param poiManager used to update the points of interest found during 
     * scan
    **************************************************************************/
    @Override
    public void updateStatus(DroneStatus droneStatus, PreviousResult previousResult, POIManager poiManager) {
        droneStatus.getBatteryManager().consumeBattery(previousResult.getCost());
    }
}
