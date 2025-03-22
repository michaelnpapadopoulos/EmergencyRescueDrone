package ca.mcmaster.se2aa4.island.team45;

import java.io.StringReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.json.JSONTokener;

import ca.mcmaster.se2aa4.island.team45.decision_stages.StageManager;
import ca.mcmaster.se2aa4.island.team45.drone.DroneConfiguration;
import ca.mcmaster.se2aa4.island.team45.drone.DroneStatus;
import ca.mcmaster.se2aa4.island.team45.drone.FlightManager;
import ca.mcmaster.se2aa4.island.team45.drone.battery.SimpleBatteryManager;
import ca.mcmaster.se2aa4.island.team45.drone.commands.CommandCenter;
import ca.mcmaster.se2aa4.island.team45.drone.commands.PreviousResult;
import ca.mcmaster.se2aa4.island.team45.drone.direction.Direction;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.map.coordinates.CoordinateManager;
import ca.mcmaster.se2aa4.island.team45.map.interest_points.POIManager;
import eu.ace_design.island.bot.IExplorerRaid;


public class Explorer implements IExplorerRaid {

    private final Logger logger = LogManager.getLogger();
    private FlightManager flightManager;

    @Override
    public void initialize(String s) {
        logger.info("** Initializing the Exploration Command Center");
        try {
            JSONObject info = new JSONObject(new JSONTokener(new StringReader(s)));
            logger.info("** Initialization info:\n {}", info.toString(2));
            String direction = info.getString("heading");
            Integer batteryLevel = info.getInt("budget");
            logger.info("The drone is facing {}", direction);
            logger.info("Battery level is {}", batteryLevel);

            // Create DroneStatus object that encapsulates all drone components
            DroneStatus droneStatus = new DroneStatus(
                new SimpleBatteryManager(batteryLevel),
                new CoordinateManager(),
                new DirectionManager(new Direction(direction)),
                new CommandCenter());

            // Initialize FlightManager using the builder pattern with DroneStatus
            flightManager = new FlightManager.Builder()
                .withPreviousResults(new PreviousResult())
                .withStageManager(new StageManager())
                .withPOIManager(new POIManager())
                .withDroneStatus(droneStatus)
                .build();

            // Pass initial info to FlightManager
            flightManager.initialize(new DroneConfiguration(direction, batteryLevel));
        } catch (Exception e) {
            logger.error("Error during initialization: ", e);
        }
    }

    @Override
    public String takeDecision() {
        try {
            String dec = flightManager.getDecision();
            logger.info("Decision: {}", dec);
            return dec;
        } catch (Exception e) {
            logger.error("Error while taking decision: ", e);
            return "error";
        }
    }

    @Override
    public void acknowledgeResults(String s) {
        try {
            JSONObject response = new JSONObject(new JSONTokener(new StringReader(s)));
            flightManager.acknowledgeResults(response);
            logger.info("\n\n");
        } catch (Exception e) {
            logger.error("Error while acknowledging results: ", e);
        }
    }

    @Override
    public String deliverFinalReport() {
        return "no creek found";
    }
}