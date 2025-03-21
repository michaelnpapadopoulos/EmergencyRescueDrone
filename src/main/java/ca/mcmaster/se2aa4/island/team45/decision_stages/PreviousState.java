package ca.mcmaster.se2aa4.island.team45.decision_stages;

import org.json.JSONObject;

public class PreviousState {
    private String prevAction;
    private String prevHeading;
    private int cost;
    private JSONObject extras;
    private String status;

    public String getPrevAction() {
        return prevAction;
    }

    public void setPrevAction(String prevAction) {
        this.prevAction = prevAction;
    }

    public String getPrevHeading() {
        return prevHeading;
    }

    public void setPrevHeading(String prevHeading) {
        this.prevHeading = prevHeading;
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
}