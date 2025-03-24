package ca.mcmaster.se2aa4.island.team45.drone.commands;

public class PreviousDecision {
    private String prevAction;
    private String prevHeading;
    private String prevUTurn;
   
    public void setAction(String action, String heading) {
        this.prevAction = action;
        this.prevHeading = heading;
    }

    public void setAction(String action) {
        this.prevAction = action;
    }

    public String getPrevAction() {
        return prevAction;
    }

    public String getPrevHeading() {
        return prevHeading;
    }   

    public void setPrevUTurn(String turnDirection) {
        this.prevUTurn = turnDirection;
    }

    public String getPrevUTurn() {
        return this.prevUTurn;
    }
    
    public String getOppositeUTurn() {
        if (getPrevUTurn() == null) {
            return null;
        } else {
            if (getPrevUTurn().equals("right")) {
                return "left";
            } else {
                return "right";
            }
        }
    }
}