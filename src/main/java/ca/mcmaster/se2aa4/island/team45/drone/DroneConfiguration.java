package ca.mcmaster.se2aa4.island.team45.drone;

public class DroneConfiguration {
    private final String direction;
    private final BatteryManager batteryManager;

    public DroneConfiguration(String direction, int batteryLevel) {
        this.direction = direction;
        this.batteryManager = new SimpleBatteryManager(batteryLevel);
    }

    public String getDirection() {
        return direction;
    }

    public BatteryManager getBatteryManager() {
        return batteryManager;
    }
}