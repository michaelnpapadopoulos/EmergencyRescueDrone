package ca.mcmaster.se2aa4.island.team45.drone.commands;

public class CommandCenter {
    private final PreviousDecision previousDecision;
    private final FlightCommands flightCommands;

    /**************************************************************************
     * Command center constructor
    **************************************************************************/
    public CommandCenter() {
        this.previousDecision = new PreviousDecision();
        this.flightCommands = new FlightCommands();
    }

    /**************************************************************************
     * Makes a drone action if heading or echo
     * 
     * @param action a drone action
     * @param heading a heading (N,E,S,W)
    **************************************************************************/
    public String makeAction(String action, String heading) {
        this.previousDecision.setAction(action, heading);
        return flightCommands.getAction(action, heading);
    }

    /**************************************************************************
     * Makes a drone action
     * 
     * @param action a drone action
    **************************************************************************/
    public String makeAction(String action) {
        this.previousDecision.setAction(action);
        return flightCommands.getAction(action);
    }

    /**************************************************************************
     * Checks if the previous action is a given action
     * 
     * @param action a drone action
    **************************************************************************/
    public boolean wasPrevAction(String action) {
        return this.previousDecision.getPrevAction().equals(action);
    }

    /**************************************************************************
     * Checks if the previous heading is a given heading
     * 
     * @param heading a heading (N,E,S,W)
    **************************************************************************/
    public boolean wasPrevHeading(String heading) {
        return this.previousDecision.getPrevHeading().equals(heading);
    }

    /**************************************************************************
     * Returns the previous heading
    **************************************************************************/
    public String getPrevHeading() {
        return this.previousDecision.getPrevHeading();
    }

    /**************************************************************************
     * Returns the previous decision object
    **************************************************************************/
    public PreviousDecision getPreviousDecision() {
        return this.previousDecision;
    }
}
