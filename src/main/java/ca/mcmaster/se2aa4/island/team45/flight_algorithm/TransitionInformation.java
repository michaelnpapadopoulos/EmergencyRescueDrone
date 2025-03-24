package ca.mcmaster.se2aa4.island.team45.flight_algorithm;

import ca.mcmaster.se2aa4.island.team45.drone.direction.Direction;

public class TransitionInformation { // Used to transfer information between Stages when necessary
    private String sweepString;
    private Direction sweepDirection;
    private Direction finalEdgeDirection;
    private String finalEdgeString;

    /**************************************************************************
     * Sets the direction and string the drone needs to echo in
     * 
     * @param sweepDir
    **************************************************************************/
    public void setSweepDir(String sweepDir) {
        this.sweepDirection = new Direction(sweepDir);
        this.sweepString = sweepDir;
    }

    /**************************************************************************
     * Gets the direction the drone needs to echo in
    **************************************************************************/
    public Direction getSweepDir() {
        return sweepDirection;
    }

    /**************************************************************************
     * Gets the string the drone needs to echo in
    **************************************************************************/
    public String getSweepString() {
        return sweepString;
    }

    /**************************************************************************
     * Sets the direction and string of the edge the drone must turn around at 
     * (ex. if begun E -> W)
    **************************************************************************/
    public void setFinalEdge(String finalEdge) {
        this.finalEdgeDirection = new Direction(finalEdge);
        this.finalEdgeString = finalEdge;
    }

    /**************************************************************************
     * Gets the direction of the edge the drone must turn around at 
     * (ex. if begun E -> W)
    **************************************************************************/
    public Direction getFinalEdgeDir() {
        return finalEdgeDirection;
    }

    /**************************************************************************
     * Gets the string of the edge the drone must turn around at 
     * (ex. if begun E -> W)
    **************************************************************************/
    public String getFinalEdgeString() {
        return finalEdgeString;
    }
}
