package ca.mcmaster.se2aa4.island.team45;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.island.team45.map.coordinates.Coordinate;

public class CoordinateTest {

    @Test
    public void testGetX() {
        Coordinate coord = new Coordinate(5, 10);
        assertEquals(5, coord.getX());
    }

    @Test
    public void testGetY() {
        Coordinate coord = new Coordinate(5, 10);
        assertEquals(10, coord.getY());
    }
}