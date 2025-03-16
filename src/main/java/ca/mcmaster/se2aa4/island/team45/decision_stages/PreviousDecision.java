package ca.mcmaster.se2aa4.island.team45.decision_stages;

public class PreviousDecision {
    private String prevAction;
    private char prevHeading;

    public PreviousDecision() {
        this.prevAction = "";
        this.prevHeading = ' ';
    }

    public void setPrevAction(String action) {
        this.prevAction = action;
    }

    public void setPrevHeading(char heading) {
        this.prevHeading = heading;
    }

    public String getPrevAction() {
        if (this.prevAction.equals("")) {
            return null;
        }
        return this.prevAction;
    }

    public char getPrevHeading() {
        return this.prevHeading;
    }
}
