package ca.mcmaster.se2aa4.island.team45;

import java.io.StringReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import eu.ace_design.island.bot.IExplorerRaid;
import org.json.JSONObject;
import org.json.JSONTokener;
import ca.mcmaster.se2aa4.island.team45.drone.FlightManager;

public class Explorer implements IExplorerRaid {

    private final Logger logger = LogManager.getLogger();

    @Override
    public void initialize(String s) {
        logger.info("** Initializing the Exploration Command Center");
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(s)));

        logger.info("** Initialization info:\n {}",info.toString(2));
        String direction = info.getString("heading");
        Integer batteryLevel = info.getInt("budget");
        logger.info("The drone is facing {}", direction);
        logger.info("Battery level is {}", batteryLevel);
        FlightManager.getInstance().passInitialInfo(direction, batteryLevel);
    }

    @Override
    public String takeDecision() {
        String dec = FlightManager.getInstance().getDecision();
        logger.info("Decision: {}", dec);
        return dec;
    }

    @Override
    public void acknowledgeResults(String s) {
        JSONObject response = new JSONObject(new JSONTokener(new StringReader(s)));
        FlightManager.getInstance().acknowledgeResults(response);
    }

    @Override
    public String deliverFinalReport() {
        return "no creek found";
    }

}
