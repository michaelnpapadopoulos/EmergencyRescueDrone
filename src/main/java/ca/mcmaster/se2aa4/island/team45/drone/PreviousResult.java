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

    public int getRange() {
        if (getExtras() == null) {
            return -1;
        } else if (!getExtras().has("range")) {
            return -1;
        }
        return getExtras().getInt("range");
    }

    public JSONArray getBiomes() {
        if (getExtras() == null) {
            return null;
        } else if (!getExtras().has("biomes")) {
            return null;
        }
        return getExtras().getJSONArray("biomes");
    }

    public boolean noLand() {
        JSONArray biomes = getBiomes();
        if (biomes == null) {
            return false;
        } else {
            for(int i = 0; i < biomes.length(); i++) {
                if (!biomes.getString(i).equals("OCEAN") || !biomes.getString(i).equals("LAKE")) {
                    return false;
                }
            }
        }
        return true;
    }
}
