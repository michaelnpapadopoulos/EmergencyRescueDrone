package ca.mcmaster.se2aa4.island.team45.drone.battery;

public class SimpleBatteryManager implements BatteryManager {
    private int batteryLevel;

    public SimpleBatteryManager(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    @Override
    public void consumeBattery(int consumption) {
        this.batteryLevel -= consumption;
    }

    @Override
    public int getBatteryLevel() {
        return this.batteryLevel;
    }

    @Override
    public void setBatteryLevel(int level) {
        this.batteryLevel = level;
    }
}