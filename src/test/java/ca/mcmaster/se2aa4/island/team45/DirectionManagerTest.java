package ca.mcmaster.se2aa4.island.team45;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.island.team45.drone.direction.Direction;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;

public class DirectionManagerTest {

    private DirectionManager directionManager;

    @BeforeEach
    public void setUp() {
        directionManager = new DirectionManager(new Direction("N"));
    }

    @Test
    public void testSetDirection() {
        directionManager.setDirection("E");
        assertEquals("E", directionManager.getDirection().toString());
    }

    @Test
    public void testGetDirection() {
        assertEquals("N", directionManager.getDirection().toString());
    }
}