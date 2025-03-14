package ca.mcmaster.se2aa4.island.team45.drone;

import org.json.JSONObject;

public class FlightCommands {
    private static FlightCommands instance = new FlightCommands();
    private final String action = "action";

    private FlightCommands() {}

    public static FlightCommands getInstance() {
        return instance;
    }

    public String fly() {
        JSONObject decision = new JSONObject();
        decision.put(action, "fly");
        return decision.toString();
    }

    public String heading(char dir) {
        JSONObject decision = new JSONObject();
        JSONObject param = new JSONObject();

        decision.put(action, "heading");
        param.put("direction", dir);
        decision.put("parameters", param);
        return decision.toString();
    }

    public String echo (char dir) {
        JSONObject decision = new JSONObject();
        JSONObject param = new JSONObject();

        decision.put(action, "echo");
        param.put("direction", dir);
        decision.put("parameters", param);
        return decision.toString();
    }

    public String scan() {
        JSONObject decision = new JSONObject();

        decision.put(action, "scan");
        return decision.toString();
    }

    public String stop() {
        JSONObject decision = new JSONObject();

        decision.put(action, "stop");
        return decision.toString();
    }
}
