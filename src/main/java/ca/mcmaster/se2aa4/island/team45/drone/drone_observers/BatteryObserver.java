package ca.mcmaster.se2aa4.island.team45.drone.drone_observers;

import ca.mcmaster.se2aa4.island.team45.drone.DroneStatus;
import ca.mcmaster.se2aa4.island.team45.drone.commands.PreviousResult;

public class BatteryObserver implements StatusObserver {
    @Override
    public void updateStatus(DroneStatus droneStatus, PreviousResult previousResult) {
        droneStatus.getBatteryManager().consumeBattery(previousResult.getCost());
    }
}
