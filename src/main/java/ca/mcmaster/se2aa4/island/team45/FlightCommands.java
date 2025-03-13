package ca.mcmaster.se2aa4.island.team45;

import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.DirectionManager;
import ca.mcmaster.se2aa4.island.CoordinateManager;
import ca.mcmaster.se2aa4.island.Direction;

public class FlightCommands {
    private String decisionKey = "";
    private DirectionManager dm;
    private CoordinateManager cm;

    public FlightCommands(DirectionManager dm, CoordinateManager cm) {
        this.dm = dm;
        this.cm = cm;
    }

    public String fly() {
        JSONObject decision = new JSONObject();
        decision.put(decisionKey, "fly");
        cm.increment()
        return decision.toString();
    }

    public String heading(Direction dir) {
        JSONObject decision = new JSONObject();
        JSONObject params = new JSONObject();

        decision.put(decisionKey, "heading");
        params.put("direction", dir);
        decision.put("parameters", params);

        cm.increment()
        dm.changeDirection(dir);
        cm.increment();

        return decision.toString();
    }

    public String echo (Direction dir) {
        JSONObject decision = new JSONObject();
        JSONObject params = new JSONObject();

        decision.put(decisionKey, "echo");
        params.put("direction", dir);
        decision.put("parameters", params);

        return decision.toString();
    }

    public String scan() {
        JSONObject decision = new JSONObject();
        decision.put(decisionKey, "scan");
        return decision.toString();
    }

    public String stop() {
        JSONObject decision = new JSONObject();
        decision.put(decisionKey, "stop");
        return decision.toString();
    }
}