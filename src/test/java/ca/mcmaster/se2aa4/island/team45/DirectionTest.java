package ca.mcmaster.se2aa4.island.team45;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.island.team45.drone.direction.Direction;

public class DirectionTest {

    private Direction direction;

    @BeforeEach
    public void setUp() {
        direction = new Direction("N");
    }

    @Test
    public void testInitialDirection() {
        assertEquals("N", direction.toString());
    }

    @Test
    public void testGetLeft() {
        assertEquals("W", direction.getLeft());
    }

    @Test
    public void testGetRight() {
        assertEquals("E", direction.getRight());
    }

    @Test
    public void testEquals() {
        Direction sameDirection = new Direction("N");
        Direction differentDirection = new Direction("E");
        assertTrue(direction.equals(sameDirection));
        assertFalse(direction.equals(differentDirection));
    }

    @Test
    public void testToFullString() {
        assertEquals("North", direction.toFullString());
    }
}