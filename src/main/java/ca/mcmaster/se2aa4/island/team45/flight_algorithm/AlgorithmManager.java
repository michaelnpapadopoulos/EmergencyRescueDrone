package ca.mcmaster.se2aa4.island.team45.flight_algorithm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.team45.drone.*;
import ca.mcmaster.se2aa4.island.team45.flight_algorithm.finding_island.stages.FirstEcho;
import ca.mcmaster.se2aa4.island.team45.flight_algorithm.finding_island.transitions.FirstEchoTrans;
import ca.mcmaster.se2aa4.island.team45.map.interest_points.*;

public class AlgorithmManager {
    private final Logger logger = LogManager.getLogger();

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
    public void setStage(Stage newStage) {
        this.currentStage = newStage;
    }

    /**************************************************************************
     * Sets the drones next transisition phase
     * 
     * @param newTransition the next transition you want to move into
    **************************************************************************/
    public void setTransition(Transition newTransition) {
        this.currentTransition = newTransition;
    }

    /**************************************************************************
     * Returns the transition info object
    **************************************************************************/
    public TransitionInformation getTransitionInfo() {
        return transitionInformation;
    }

    /**************************************************************************
     * Checks if the drone needs to transition into a new stage then makes a 
     * decision within the current stage and return a drone action
     * 
     * @param droneStatus the drones status object
     * @param poiManager the programs poi manager object
     * @param previousResult the drons previous result object
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

                String decision = currentStage.makeDecision(droneStatus.getDirectionManager(), droneStatus.getCommandCenter(), this);

                logger.info("\n** Current Stage: {}\n** Transition: {}", currentStage.getClass().getSimpleName(), currentTransition.getClass().getSimpleName());
                if (currentStage.getClass().getSimpleName().equals("Scan")) {
                    logger.info("==== ALL ISLAND EDGES FOUND ====\nNorth: {}\nEast: {}\nSouth: {}\nWest: {}", islandEdgeManager.getEdge("North").printCord(), islandEdgeManager.getEdge("East").printCord(), islandEdgeManager.getEdge("South").printCord(), islandEdgeManager.getEdge("West").printCord());
                }

                return decision;
    }
}