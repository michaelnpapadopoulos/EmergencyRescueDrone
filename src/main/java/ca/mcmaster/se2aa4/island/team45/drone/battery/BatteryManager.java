package ca.mcmaster.se2aa4.island.team45.drone.battery;

public interface BatteryManager {
    void consumeBattery(int consumption);
    int getBatteryLevel();
    void setBatteryLevel(int level);
}
