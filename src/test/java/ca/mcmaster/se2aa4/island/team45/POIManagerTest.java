package ca.mcmaster.se2aa4.island.team45;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.island.team45.map.coordinates.Coordinate;
import ca.mcmaster.se2aa4.island.team45.map.interest_points.Creek;
import ca.mcmaster.se2aa4.island.team45.map.interest_points.POIManager;
import ca.mcmaster.se2aa4.island.team45.map.interest_points.Site;

public class POIManagerTest {

    private POIManager poiManager;

    @BeforeEach
    public void setUp() {
        poiManager = new POIManager();
    }

    @Test
    public void testAddCreek() {
        Creek creek = new Creek(new Coordinate(1, 1), "creek1");
        poiManager.addCreek(creek);
        assertTrue(poiManager.hasCreeks());
    }

    @Test
    public void testSetSiteLocation() {
        Site site = new Site(new Coordinate(1, 1), "site1");
        poiManager.setSiteLocation(site);
        assertTrue(poiManager.hasSite());
    }
}