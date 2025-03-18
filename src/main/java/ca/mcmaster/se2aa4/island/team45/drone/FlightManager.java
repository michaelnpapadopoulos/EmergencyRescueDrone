package ca.mcmaster.se2aa4.island.team45.drone;

import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.team45.decision_stages.StageManager;
import ca.mcmaster.se2aa4.island.team45.map.POIManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FlightManager {
    private static final FlightManager fm = new FlightManager();
    private final Logger logger = LogManager.getLogger();

    private DirectionManager direction;
    private BatteryManager battery;
    private PreviousResult previousResult;
    private StageManager stageManager;
    private POIManager poiManager;

    private FlightManager() {}

    public static FlightManager getInstance() {
        return fm;
    }

    public void passInitialInfo(String direction, int batteryLevel) { // Passes initial information about the drone from the initialize() method in Explorer
        this.direction = new DirectionManager(direction);
        this.battery = new BatteryManager(batteryLevel);
        this.previousResult = new PreviousResult(0, null, direction);
        this.stageManager = new StageManager();
        this.poiManager = new POIManager();
    }
    
    public String getDecision() {
        return this.stageManager.makeStageDecision(direction, battery, previousResult, this.stageManager, this.poiManager);
    }

    public void acknowledgeResults(JSONObject results) {
        logger.info("** Acknowledging results...");
        int cost = results.getInt("cost");
        String status = results.getString("status");
        JSONObject extraInfo = results.getJSONObject("extras");

        previousResult.setPreviousResult(cost, status, extraInfo);
        battery.consumeBattery(cost);
    }
    
    
}
