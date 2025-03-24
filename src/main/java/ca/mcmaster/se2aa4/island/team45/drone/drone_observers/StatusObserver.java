package ca.mcmaster.se2aa4.island.team45.drone.drone_observers;

import ca.mcmaster.se2aa4.island.team45.drone.*;
import ca.mcmaster.se2aa4.island.team45.map.interest_points.POIManager;


public interface StatusObserver {
    void updateStatus(DroneStatus droneStatus, PreviousResult previousResult, POIManager poiManager);
}
