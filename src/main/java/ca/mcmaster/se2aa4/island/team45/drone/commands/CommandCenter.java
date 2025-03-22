package ca.mcmaster.se2aa4.island.team45.drone.commands;

public class CommandCenter {
    private PreviousDecision previousDecision;
    private FlightCommands flightCommands;

    public CommandCenter() {
        this.previousDecision = new PreviousDecision();
        this.flightCommands = new FlightCommands();
    }

    public String makeAction(String action, String heading) {
        this.previousDecision.setAction(action, heading);
        return flightCommands.getAction(action, heading);
    }

    public String makeAction(String action) {
        this.previousDecision.setAction(action);
        return flightCommands.getAction(action);
    }

    public String getPrevAction() {
        return this.previousDecision.getPrevAction();
    }

    public String getPrevHeading() {
        return this.previousDecision.getPrevHeading();
    }

    public PreviousDecision getPreviousDecision() {
        return this.previousDecision;
    }
}
