package ca.mcmaster.se2aa4.island.team45.drone.commands;

import org.json.JSONObject;

public class FlightCommands {
    private final String action = "action";

    /**************************************************************************
     * Returns the action that the algorithm wants the drone to make if echo or
     * heading
     *
     * @param action the action we want the drone to perform
     * @param heading sets the heading of that action
    **************************************************************************/
    public String getAction(String action, String heading) {
        return switch (action) {
            case "heading" -> heading(heading);
            case "echo" -> echo(heading);
            default -> null;
        };
    }

    /**************************************************************************
     * Returns the action that the algorithm wants the drone to make
     *
     * @param action the action we want the drone to perform
    **************************************************************************/
    public String getAction(String action) {
        return switch (action) {
            case "stop" -> stop();
            case "fly" -> fly();
            case "scan" -> scan();
            default -> null;
        };
    }

    /**************************************************************************
     * Makes fly decision
    **************************************************************************/
    private String fly() {
        JSONObject decision = new JSONObject();
        decision.put(action, "fly");
        return decision.toString();
    }

    /**************************************************************************
     * Makes heading decision
     * 
     * @param dir direction to change heading
    **************************************************************************/
    private String heading(String dir) {
        JSONObject decision = new JSONObject();
        JSONObject param = new JSONObject();

        decision.put(action, "heading");
        param.put("direction", dir);
        decision.put("parameters", param);
        return decision.toString();
    }

    /**************************************************************************
     * Makes echo decision
     * 
     * @param dir direction to echo
    **************************************************************************/
    private String echo (String dir) {
        JSONObject decision = new JSONObject();
        JSONObject param = new JSONObject();

        decision.put(action, "echo");
        param.put("direction", dir);
        decision.put("parameters", param);
        return decision.toString();
    }

    /**************************************************************************
     * Makes scan decision
    **************************************************************************/
    private String scan() {
        JSONObject decision = new JSONObject();

        decision.put(action, "scan");
        return decision.toString();
    }

    /**************************************************************************
     * Makes stop decision
    **************************************************************************/
    private String stop() {
        JSONObject decision = new JSONObject();

        decision.put(action, "stop");
        return decision.toString();
    }
}
