package ca.mcmaster.se2aa4.island.team45.drone;

import org.json.JSONArray;
import org.json.JSONObject;

public class PreviousResult {
    private int cost;
    private JSONObject extras;
    private String status;

    public PreviousResult(int cost, JSONObject extras, String status) {
        this.cost = cost;
        this.extras = extras;
        this.status = status;
    }

    public void setPreviousResult(int cost, String status, JSONObject extras) {
        this.cost = cost;
        this.extras = extras;
        this.status = status;
    }

    public int getCost() {
        return cost;
    }

    public String getStatus() {
        return status;
    }

    private JSONObject getExtras() {
        if (extras == null || extras.length() == 0) {
            return null;
        }
        return extras;
    }

    public String getFound() {
        if (getExtras() == null) {
            return null;
        } else if (!getExtras().has("found")) {
            return null;
        }
        return getExtras().getString("found");
    }

    public String getRange() {
        if (getExtras() == null) {
            return null;
        } else if (!getExtras().has("range")) {
            return null;
        }
        return getExtras().getString("range");
    }

    public boolean getLand() {
        JSONArray biomes = extras.optJSONArray("biomes");
        if(biomes == null) {
            return false;
        }
        for (int i = 0; i < biomes.length(); i++) {
            if (biomes.getString(i).equals("OCEAN")) {
                return false;
            }
        }

        return true;
    }
}
