package ca.mcmaster.se2aa4.island.team45;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.island.team45.drone.DroneStatus;
import ca.mcmaster.se2aa4.island.team45.drone.battery.SimpleBatteryManager;
import ca.mcmaster.se2aa4.island.team45.drone.commands.CommandCenter;
import ca.mcmaster.se2aa4.island.team45.drone.direction.Direction;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.map.coordinates.CoordinateManager;

public class DroneStatusTest {

    private DroneStatus droneStatus;
    private SimpleBatteryManager batteryManager;
    private CoordinateManager coordinateManager;
    private DirectionManager directionManager;
    private CommandCenter commandCenter;

    @BeforeEach
    public void setUp() {
        batteryManager = new SimpleBatteryManager(100);
        coordinateManager = new CoordinateManager();
        directionManager = new DirectionManager(new Direction("N"));
        commandCenter = new CommandCenter();

        droneStatus = new DroneStatus(batteryManager, coordinateManager, directionManager, commandCenter);
    }

    @Test
    public void testGetBatteryManager() {
        assertEquals(batteryManager, droneStatus.getBatteryManager());
    }

    @Test
    public void testGetCoordinateManager() {
        assertEquals(coordinateManager, droneStatus.getCoordinateManager());
    }

    @Test
    public void testGetDirectionManager() {
        assertEquals(directionManager, droneStatus.getDirectionManager());
    }

    @Test
    public void testGetCommandCenter() {
        assertEquals(commandCenter, droneStatus.getCommandCenter());
    }
}