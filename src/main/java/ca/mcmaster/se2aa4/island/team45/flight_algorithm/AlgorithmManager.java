package ca.mcmaster.se2aa4.island.team45.flight_algorithm;

import ca.mcmaster.se2aa4.island.team45.drone.DroneStatus;
import ca.mcmaster.se2aa4.island.team45.drone.PreviousResult;
import ca.mcmaster.se2aa4.island.team45.flight_algorithm.finding_island.stages.FirstEcho;
import ca.mcmaster.se2aa4.island.team45.flight_algorithm.finding_island.transitions.FirstEchoTrans;
import ca.mcmaster.se2aa4.island.team45.map.interest_points.IslandEdgeManager;
import ca.mcmaster.se2aa4.island.team45.map.interest_points.POIManager;

public class AlgorithmManager {
    private Stage currentStage;
    private Transition currentTransition;
    private final IslandEdgeManager islandEdgeManager;
    private final TransitionInformation transitionInformation;
    
    /**************************************************************************
     * Algorithm manager constructor
    **************************************************************************/
    public AlgorithmManager() {
        this.currentStage = new FirstEcho();
        this.islandEdgeManager = new IslandEdgeManager();
        this.currentTransition = new FirstEchoTrans();
        this.transitionInformation = new TransitionInformation();
    }

    /**************************************************************************
     * Sets the drones next stage phase
     * 
     * @param newStage the next stage you want to move into
    **************************************************************************/
    public void setStage(Stage newStage) { this.currentStage = newStage;}

    /**************************************************************************
     * Sets the drones next transition phase
     * 
     * @param newTransition the next transition you want to move into
    **************************************************************************/
    public void setTransition(Transition newTransition) { this.currentTransition = newTransition; }

    /**************************************************************************
     * @return the current stage object for testing purposes
     * ************************************************************************/
    public Stage getCurrentStage() { return currentStage;}

    /**************************************************************************
     * Returns the transition info object
    **************************************************************************/
    public TransitionInformation getTransitionInfo() { return transitionInformation; }

    /**************************************************************************
     * @return the current transition object for testing purposes
     * ************************************************************************/
    public Transition getCurrentTransition() { return currentTransition; }

    /**************************************************************************
     * Checks if the drone needs to transition into a new stage then makes a 
     * decision within the current stage and return a drone action
     * 
     * @param droneStatus the drones status object
     * @param poiManager the programs poi manager object
     * @param previousResult the drones previous result object
    **************************************************************************/
    public String makeStageDecision(
            DroneStatus droneStatus,
            POIManager poiManager,
            PreviousResult previousResult) {

                currentTransition.makeTransition(
                    droneStatus.getDirectionManager(),
                    droneStatus.getBatteryManager(),
                    this.islandEdgeManager, 
                    previousResult, 
                    this, 
                    droneStatus.getCommandCenter().getPreviousDecision(),
                    droneStatus.getCoordinateManager());

                return currentStage.makeDecision(droneStatus.getDirectionManager(), droneStatus.getCommandCenter(), this);
    }
}