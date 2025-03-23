package ca.mcmaster.se2aa4.island.team45;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.island.team45.drone.battery.SimpleBatteryManager;

public class SimpleBatteryManagerTest {

    @Test
    public void testConsumeBattery() {
        SimpleBatteryManager batteryManager = new SimpleBatteryManager(100);
        batteryManager.consumeBattery(10);
        assertEquals(90, batteryManager.getBatteryLevel());
    }

    @Test
    public void testSetBatteryLevel() {
        SimpleBatteryManager batteryManager = new SimpleBatteryManager(100);
        batteryManager.setBatteryLevel(50);
        assertEquals(50, batteryManager.getBatteryLevel());
    }
}