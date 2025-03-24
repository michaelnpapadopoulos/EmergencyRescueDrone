package ca.mcmaster.se2aa4.island.team45.drone;

import ca.mcmaster.se2aa4.island.team45.drone.battery.*;

public class DroneConfiguration {
    private final String direction;
    private final BatteryManager batteryManager;

    /**************************************************************************
     * Constructs a drone configuration object
     *
     * @param direction direction the drone is initially facing
     * @param batteryLevel initial battery level of the drone
    **************************************************************************/
    public DroneConfiguration(String direction, int batteryLevel) {
        this.direction = direction;
        this.batteryManager = new SimpleBatteryManager(batteryLevel);
    }

    /**************************************************************************
     * Returns the drones initial direction
    **************************************************************************/
    public String getDirection() {
        return direction;
    }

    /**************************************************************************
     * Returns the drones battery manager
    **************************************************************************/
    public BatteryManager getBatteryManager() {
        return batteryManager;
    }
}