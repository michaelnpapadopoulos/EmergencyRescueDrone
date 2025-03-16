package ca.mcmaster.se2aa4.island.team45.decision_stages;

public class PreviousDecision {
    private String prevAction; // Prim obssesed
    private String prevHeading;

    public PreviousDecision() {
        this.prevAction = "";
        this.prevHeading = "";
    }

    public void setPrevAction(String action) {
        this.prevAction = action;
    }

    public void setPrevHeading(String heading) {
        this.prevHeading = heading;
    }

    public String getPrevAction() {
        if (this.prevAction.equals("")) {
            return null;
        }
        return this.prevAction;
    }

    public String getPrevHeading() {
        return this.prevHeading;
    }
}
