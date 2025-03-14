package ca.mcmaster.se2aa4.island.team45.decision_stages;

public class PreviousDecision {
    private String prevAction;

    public PreviousDecision() {
        this.prevAction = "";
    }

    public void setPrevAction(String action) {
        this.prevAction = action;
    }

    public String getPrevAction() {
        if (this.prevAction.equals("")) {
            return null;
        }
        return this.prevAction;
    }
}
