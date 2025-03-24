package ca.mcmaster.se2aa4.island.team45;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.island.team45.drone.DroneStatus;
import ca.mcmaster.se2aa4.island.team45.drone.PreviousResult;
import ca.mcmaster.se2aa4.island.team45.drone.battery.SimpleBatteryManager;
import ca.mcmaster.se2aa4.island.team45.drone.commands.CommandCenter;
import ca.mcmaster.se2aa4.island.team45.drone.direction.Direction;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.flight_algorithm.AlgorithmManager;
import ca.mcmaster.se2aa4.island.team45.map.coordinates.CoordinateManager;
import ca.mcmaster.se2aa4.island.team45.map.interest_points.POIManager;

public class AlgorithmManagerTest {

    private AlgorithmManager algorithmManager;
    private DroneStatus droneStatus;
    private POIManager poiManager;
    private PreviousResult previousResult;

    @BeforeEach
    public void setUp() {
        algorithmManager = new AlgorithmManager();
        droneStatus = new DroneStatus(
            new SimpleBatteryManager(100),
            new CoordinateManager(),
            new DirectionManager(new Direction("N")),
            new CommandCenter()
        );
        poiManager = new POIManager();
        previousResult = new PreviousResult();
    }

    @Test
    public void testInitialStage() {
        assertNotNull(algorithmManager);
        assertEquals("FirstEcho", algorithmManager.getCurrentStage().getClass().getSimpleName());
    }

    @Test
    public void testInitialTransition() {
        assertNotNull(algorithmManager);
        assertEquals("FirstEchoTrans", algorithmManager.getCurrentTransition().getClass().getSimpleName());
    }

    @Test
    public void testMakeStageDecision() {
        String decision = algorithmManager.makeStageDecision(droneStatus, poiManager, previousResult);
        assertNotNull(decision);
        assertTrue(decision.contains("echo"));
    }
}