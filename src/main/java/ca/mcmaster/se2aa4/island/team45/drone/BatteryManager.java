package ca.mcmaster.se2aa4.island.team45.drone;

public class BatteryManager { // Keeps track of drones battery level, providing methods to consume and check battery level
    private int batteryLevel;

    public BatteryManager(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public void consumeBattery(int consumption) {
        this.batteryLevel -= consumption;
    }

    public int getBatteryLevel() {
        return this.batteryLevel;
    }
}
