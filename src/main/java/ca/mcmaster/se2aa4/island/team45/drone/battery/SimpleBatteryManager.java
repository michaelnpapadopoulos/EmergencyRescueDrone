package ca.mcmaster.se2aa4.island.team45.drone.battery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SimpleBatteryManager implements BatteryManager {
    private final Logger logger = LogManager.getLogger();
    private int batteryLevel;

    /**************************************************************************
     * Simple battery manager constructor
     * 
     * @param batteryLevel initial drone battery
    **************************************************************************/
    public SimpleBatteryManager(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    /**************************************************************************
     * Reduces the drones battery level by a consumption
     * 
     * @param consumption the cost of a drone action
    **************************************************************************/
    @Override
    public void consumeBattery(int consumption) {
        logger.info("Consuming {} battery", consumption);
        this.batteryLevel -= consumption;
    }

    /**************************************************************************
     * Returns the drones battery level
    **************************************************************************/
    @Override
    public int getBatteryLevel() { return batteryLevel; }

    /**************************************************************************
     * Sets the drones battery level
     * 
     * @param level drone battery level
    **************************************************************************/
    @Override
    public void setBatteryLevel(int level) { this.batteryLevel = level; }
}