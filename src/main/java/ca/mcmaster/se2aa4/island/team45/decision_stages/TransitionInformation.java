package ca.mcmaster.se2aa4.island.team45.decision_stages;

import ca.mcmaster.se2aa4.island.team45.drone.direction.Direction;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionEnum;

public class TransitionInformation { // Used to transfer information between Stages when necessary
    private String sweepString;
    private Direction sweepDirection;
    private Direction finalEdgeDirection;
    private String finalEdgeString;

    public void setSweepDir(DirectionEnum sweepDir) {
        this.sweepDirection = new Direction(sweepDir);
        this.sweepString = sweepDir.getShortDir();
    }

    public Direction getSweepDir() {
        return sweepDirection;
    }

    public String getSweepString() {
        return sweepString;
    }

    public void setFinalEdge(DirectionEnum finalEdge) {
        this.finalEdgeDirection = new Direction(finalEdge);
        this.finalEdgeString = finalEdge.getShortDir();
    }

    public Direction getFinalEdgeDir() {
        return finalEdgeDirection;
    }

    public String getFinalEdgeString() {
        return finalEdgeString;
    }
}
