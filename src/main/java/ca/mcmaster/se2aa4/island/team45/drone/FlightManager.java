package ca.mcmaster.se2aa4.island.team45.drone;

import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.team45.map.CoordinateManager;
import netscape.javascript.JSObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FlightManager {
    private static final FlightManager fm = new FlightManager();
    
    private String direction;
    private int batteryLevel;

    private final Logger logger = LogManager.getLogger();
    private int count = 5;

    private FlightManager() {}

    public static FlightManager getInstance() {
        return fm;
    }

    public void passInitialInfo(String direction, int batteryLevel) {
        this.direction = direction;
        this.batteryLevel = batteryLevel;
    }
    
    public String makeDecision() {
        logger.info("Making decision...");
        JSONObject decision = new JSONObject();

        if (this.count > 0) { 
            this.count--;
            decision.put("action", "fly");
        } else if (this.count == 0) {
            this.count--;
            decision.put("action", "scan");
        } else {
            decision.put("action", "stop");
        }

        return decision.toString();
    }
    
    
}
