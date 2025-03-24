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

    // @Test
    // public void testIncrementX() {
    //     Coordinate coord = new Coordinate(5, 10);
    //     int coordX = coord.getX();
    //     coord.incrementX();
    //     assertEquals(6, coordX);
    // }

    // @Test
    // public void testDecrementY() {
    //     Coordinate coord = new Coordinate(5, 10);
    //     int coordY = coord.getY();
    //     coord.decrementY();
    //     assertEquals(9, coordY);
    // }
}