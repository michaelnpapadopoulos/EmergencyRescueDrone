package ca.mcmaster.se2aa4.island.team45.drone;

import org.json.JSONArray;
import org.json.JSONObject;

public class PreviousResult {
    private int cost;
    private String status;
    private JSONObject extras;

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
    
    
    public boolean getFound(String foundType) {
        if (getExtras() == null) {
            return false;
        } else if (!getExtras().has("found")) {
            return false;
        }
        return getExtras().getString("found").equals(foundType);
    }

    private JSONObject getExtras() {
        if (extras == null || extras.length() == 0) {
            return null;
        }
        return extras;
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

    public JSONArray getSites() {
        if (getExtras() == null) {
            return null;
        } else if (!getExtras().has("sites")) {
            return null;
        } else if (getExtras().getJSONArray("sites").isEmpty()) {
            return null;
        } else {
            return getExtras().getJSONArray("sites");
        }
    }

    public JSONArray getCreeks() {
        if (getExtras() == null) {
            return null;
        } else if (!getExtras().has("creeks")) {
            return null;
        } else if (getExtras().getJSONArray("creeks").isEmpty()) {
            return null;
        } else {
            return getExtras().getJSONArray("creeks");
        }
    }


    public String[] retrieveSites() {
        JSONArray sites = getSites();
        if (sites == null) {
            return null;
        }

        String[] siteList = new String[sites.length()];
        for (int i = 0; i < sites.length(); i++) {
            siteList[i] = sites.getString(i);
        }

        return siteList;
    }

    

    public String[] retrieveCreeks() {
        JSONArray creeks = getCreeks();
        if (creeks == null) {
            return null;
        }

        String[] creekList = new String[creeks.length()];
        for (int i = 0; i < creeks.length(); i++) {
            creekList[i] = creeks.getString(i);
        }

        return creekList;
    }
}
