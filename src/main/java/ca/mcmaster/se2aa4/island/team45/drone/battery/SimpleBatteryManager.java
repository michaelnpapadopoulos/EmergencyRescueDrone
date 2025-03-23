package ca.mcmaster.se2aa4.island.team45.drone.battery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SimpleBatteryManager implements BatteryManager {
    private final Logger logger = LogManager.getLogger();
    private int batteryLevel;

    public SimpleBatteryManager(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    @Override
    public void consumeBattery(int consumption) {
        logger.info("Consuming {} battery", consumption);
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