package ca.mcmaster.se2aa4.island.team45.drone;

public interface BatteryManager {
    void consumeBattery(int consumption);
    int getBatteryLevel();
    void setBatteryLevel(int level);
}
