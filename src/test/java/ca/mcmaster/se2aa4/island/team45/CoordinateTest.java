package ca.mcmaster.se2aa4.island.team45;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.island.team45.map.coordinates.CoordValue;
import ca.mcmaster.se2aa4.island.team45.map.coordinates.Coordinate;

public class CoordinateTest {

    @Test
    public void testGetX() {
        Coordinate coord = new Coordinate(new CoordValue(5), new CoordValue(10));
        assertEquals(5, coord.getX().getCoordVal());
    }

    @Test
    public void testGetY() {
        Coordinate coord = new Coordinate(new CoordValue(5), new CoordValue(10));
        assertEquals(10, coord.getY().getCoordVal());
    }

    @Test
    public void testIncrementX() {
        Coordinate coord = new Coordinate(new CoordValue(5), new CoordValue(10));
        coord.incrementX();
        assertEquals(6, coord.getX().getCoordVal());
    }

    @Test
    public void testDecrementY() {
        Coordinate coord = new Coordinate(new CoordValue(5), new CoordValue(10));
        coord.decrementY();
        assertEquals(9, coord.getY().getCoordVal());
    }
}