package ca.mcmaster.se2aa4.island.team45.drone;

public class BatteryManager {
    private int batteryLevel;

    public BatteryManager(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public void consumeBattery(int consumption) {
        this.batteryLevel -= consumption;
    }
}
