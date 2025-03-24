package ca.mcmaster.se2aa4.island.team45.drone.commands;

public class PreviousDecision {
    private String prevAction;
    private String prevHeading;
    private String prevUTurn;
   
    /**************************************************************************
     * Sets previous action of the drone if heading or echo
     *
     * @param action sets previous action
     * @param heading sets previous heading
    **************************************************************************/
    public void setAction(String action, String heading) {
        this.prevAction = action;
        this.prevHeading = heading;
    }

    /**************************************************************************
     * Sets the previous action of the drone
     *
     * @param action the previous action of the drone
    **************************************************************************/
    public void setAction(String action) {
        this.prevAction = action;
    }

    /**************************************************************************
     * Gets the previous action made by the drone
    **************************************************************************/
    public String getPrevAction() {
        return prevAction;
    }

    /**************************************************************************
     * Gets the previous direction a echo or heading change was made in
    **************************************************************************/
    public String getPrevHeading() {
        return prevHeading;
    }   

    /**************************************************************************
     * Sets the Previous decision's previous uturn
     *
     * @param turnDirection a turn direction ("left", "right")
    **************************************************************************/
    public void setPrevUTurn(String turnDirection) {
        this.prevUTurn = turnDirection;
    }

    /**************************************************************************
     * gets the previous uturn direction ("left", "right")
    **************************************************************************/
    public String getPrevUTurn() {
        return this.prevUTurn;
    }
    
    /**************************************************************************
     * Gets the opposite to the previous uturn ("left", "right")
    **************************************************************************/
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