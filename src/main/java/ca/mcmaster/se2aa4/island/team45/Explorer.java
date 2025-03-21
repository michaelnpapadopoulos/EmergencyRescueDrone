package ca.mcmaster.se2aa4.island.team45;

import java.io.StringReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.json.JSONTokener;

import ca.mcmaster.se2aa4.island.team45.decision_stages.StageManager;
import ca.mcmaster.se2aa4.island.team45.drone.DroneConfiguration;
import ca.mcmaster.se2aa4.island.team45.drone.FlightManager;
import ca.mcmaster.se2aa4.island.team45.drone.PreviousState;
import ca.mcmaster.se2aa4.island.team45.drone.battery.SimpleBatteryManager;
import ca.mcmaster.se2aa4.island.team45.drone.direction.Direction;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.map.POIManager;
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

            // Initialize FlightManager using the builder pattern
            flightManager = new FlightManager.Builder()
                .withDirectionManager(new DirectionManager(new Direction(direction)))
                .withBatteryManager(new SimpleBatteryManager(batteryLevel))
                .withPreviousState(new PreviousState())
                .withStageManager(new StageManager())
                .withPOIManager(new POIManager())
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