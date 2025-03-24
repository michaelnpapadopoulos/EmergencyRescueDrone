package ca.mcmaster.se2aa4.island.team45;

import org.json.JSONObject;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.island.team45.drone.DroneConfiguration;
import ca.mcmaster.se2aa4.island.team45.drone.DroneStatus;
import ca.mcmaster.se2aa4.island.team45.drone.FlightManager;
import ca.mcmaster.se2aa4.island.team45.drone.PreviousResult;
import ca.mcmaster.se2aa4.island.team45.drone.battery.SimpleBatteryManager;
import ca.mcmaster.se2aa4.island.team45.drone.commands.CommandCenter;
import ca.mcmaster.se2aa4.island.team45.drone.direction.Direction;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.flight_algorithm.AlgorithmManager;
import ca.mcmaster.se2aa4.island.team45.map.coordinates.CoordinateManager;
import ca.mcmaster.se2aa4.island.team45.map.interest_points.POIManager;

public class FlightManagerTest {

    private FlightManager flightManager;
    private PreviousResult previousResult;
    private AlgorithmManager algorithmManager;
    private DroneStatus droneStatus;
    private POIManager poiManager;

    @BeforeEach
    public void setUp() {
        previousResult = new PreviousResult();
        algorithmManager = new AlgorithmManager();
        droneStatus = new DroneStatus(
            new SimpleBatteryManager(100),
            new CoordinateManager(),
            new DirectionManager(new Direction("N")),
            new CommandCenter()
        );
        poiManager = new POIManager();

        flightManager = new FlightManager.Builder()
            .withPreviousResults(previousResult)
            .withAlgorithmManager(algorithmManager)
            .withDroneStatus(droneStatus)
            .withPOIManager(poiManager)
            .build();
    }

    @Test
    public void testInitialize() {
        DroneConfiguration config = new DroneConfiguration("N", 100);
        flightManager.initialize(config);
        
        // Accessing droneStatus directly for testing purposes
        assertEquals("N", droneStatus.getDirectionManager().getDirection().toString());
        assertEquals(100, droneStatus.getBatteryManager().getBatteryLevel());
    }

    @Test
    public void testGetDecision() {
        String decision = flightManager.getDecision();
        assertNotNull(decision);
        // Assuming the initial decision is based on the initial stage in AlgorithmManager
        assertTrue(decision.equals("echo") || decision.equals("fly"));
    }

    @Test
    public void testAcknowledgeResults() {
        JSONObject results = new JSONObject();
        results.put("cost", 10);
        results.put("status", "success");
        results.put("extras", new JSONObject());

        flightManager.acknowledgeResults(results);
        assertEquals(10, previousResult.getCost());
        assertEquals("success", previousResult.getStatus());
    }
}