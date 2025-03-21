package ca.mcmaster.se2aa4.island.team45;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.island.team45.drone.direction.Direction;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;

public class DirectionManagerTest {

    @Test
    public void testSetDirection() {
        DirectionManager manager = new DirectionManager(new Direction("N"));
        manager.setDirection("E");
        assertEquals("E", manager.getDirection().stringForward());
    }
}