package ca.mcmaster.se2aa4.island.team45;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.island.team45.drone.DroneConfiguration;

public class DroneConfigurationTest {

    @Test
    public void testGetDirection() {
        DroneConfiguration config = new DroneConfiguration("N", 100);
        assertEquals("N", config.getDirection());
    }

    @Test
    public void testGetBatteryManager() {
        DroneConfiguration config = new DroneConfiguration("N", 100);
        assertNotNull(config.getBatteryManager());
    }
}