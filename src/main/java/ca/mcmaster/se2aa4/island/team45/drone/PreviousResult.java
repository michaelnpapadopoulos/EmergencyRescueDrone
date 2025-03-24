package ca.mcmaster.se2aa4.island.team45.drone;

import org.json.JSONArray;
import org.json.JSONObject;

public class PreviousResult {
    private int cost;
    private String status;
    private JSONObject extras;

    /**************************************************************************
     * Sets the previous cost, extras, and status of the drone for the previous
     * result object
     *
     * @param cost cost of the action on the drone battery
     * @param status drones status
     * @param extras the extra from the JSONObject created when you make a 
     * decision
    **************************************************************************/
    public void setPreviousResult(int cost, String status, JSONObject extras) {
        this.cost = cost;
        this.extras = extras;
        this.status = status;
    }

    /**************************************************************************
     * Gets cost of the pervious action
    **************************************************************************/
    public int getCost() {
        return cost;
    }

    /**************************************************************************
     * Gets previous drone status (initally from JSONObject)
    **************************************************************************/
    public String getStatus() {
        return status;
    }
    
    /**************************************************************************
     * Finds if "found" is equivalent to an expected "found" content
     *
     * @param foundType a possible content of "found"
    **************************************************************************/
    public boolean getFound(String foundType) {
        if (getExtras() == null) {
            return false;
        } else if (!getExtras().has("found")) {
            return false;
        }
        return getExtras().getString("found").equals(foundType);
    }

    /**************************************************************************
     * Returns the "extras" JSONObject
    **************************************************************************/
    private JSONObject getExtras() {
        if (extras == null || extras.length() == 0) {
            return null;
        }
        return extras;
    }

    /**************************************************************************
     * @return the "extras" JSONObject, but a copy of it to avoid leaky abstractions
    **************************************************************************/

    public JSONObject getExtrasCopy() {
        if (extras == null || extras.length() == 0) {
            return null;
        }
        return new JSONObject(extras);
    }

    /**************************************************************************
     * Takes the "range" int from the "extras" and returns it
    **************************************************************************/
    public int getRange() {
        if (getExtras() == null) {
            return -1;
        } else if (!getExtras().has("range")) {
            return -1;
        }
        return getExtras().getInt("range");
    }

    /**************************************************************************
     * Takes the "biomes" JSONArray from the "extras" and returns it
    **************************************************************************/
    public JSONArray getBiomes() {
        if (getExtras() == null) {
            return null;
        } else if (!getExtras().has("biomes")) {
            return null;
        }
        return getExtras().getJSONArray("biomes");
    }

    /**************************************************************************
     * Takes the "sites" JSONArray from the "extras" and returns it
    **************************************************************************/
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

    /**************************************************************************
     * Takes the "creeks" JSONArray from the "extras" and returns it
    **************************************************************************/
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

    /**************************************************************************
     * Takes the "sites" JSONArray from the "extras" and creates a string 
     * array of its contents
    **************************************************************************/
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

    /**************************************************************************
     * Takes the "creeks" JSONArray from the "extras" and creates a string 
     * array of its contents
    **************************************************************************/
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
