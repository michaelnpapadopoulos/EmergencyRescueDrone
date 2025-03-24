package ca.mcmaster.se2aa4.island.team45.drone.commands;

import org.json.JSONObject;

public class FlightCommands {
    private final String action = "action";

    public String getAction(String action, String heading) {
        switch (action) {
            case "heading":
                return heading(heading);
            case "echo":
                return echo(heading);
            default:
                return null;
        }
    }

    public String getAction(String action) {
        switch (action) {
            case "stop":
                return stop();
            case "fly":
                return fly();
            case "scan":
                return scan();
            default:
                return null;
        }
    }

    private String fly() {
        JSONObject decision = new JSONObject();
        decision.put(action, "fly");
        return decision.toString();
    }

    private String heading(String dir) {
        JSONObject decision = new JSONObject();
        JSONObject param = new JSONObject();

        decision.put(action, "heading");
        param.put("direction", dir);
        decision.put("parameters", param);
        return decision.toString();
    }

    private String echo (String dir) {
        JSONObject decision = new JSONObject();
        JSONObject param = new JSONObject();

        decision.put(action, "echo");
        param.put("direction", dir);
        decision.put("parameters", param);
        return decision.toString();
    }

    private String scan() {
        JSONObject decision = new JSONObject();

        decision.put(action, "scan");
        return decision.toString();
    }

    private String stop() {
        JSONObject decision = new JSONObject();

        decision.put(action, "stop");
        return decision.toString();
    }
}
