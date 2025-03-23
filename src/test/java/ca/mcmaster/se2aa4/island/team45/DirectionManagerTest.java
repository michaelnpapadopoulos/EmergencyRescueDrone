package ca.mcmaster.se2aa4.island.team45;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionEnum;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;

public class DirectionManagerTest {

    @Test
    public void testSetDirection() {
        DirectionManager manager = new DirectionManager(DirectionEnum.EAST);
        manager.setDirection("E");
        assertEquals("E", manager.getDirection().stringForward());
    }
}