package ca.mcmaster.se2aa4.island.team45.decision_stages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.team45.decision_stages.finding_island.FirstEcho;
import ca.mcmaster.se2aa4.island.team45.decision_stages.finding_island.transitions.FirstEchoTransition;
import ca.mcmaster.se2aa4.island.team45.drone.DroneStatus;
import ca.mcmaster.se2aa4.island.team45.drone.PreviousResult;
import ca.mcmaster.se2aa4.island.team45.map.interest_points.IslandEdgeManager;
import ca.mcmaster.se2aa4.island.team45.map.interest_points.POIManager;

public class StageManager {
    private final Logger logger = LogManager.getLogger();

    private Stage currentStage;
    private Transition currentTransition;
    private final IslandEdgeManager islandEdgeManager;
    private final TransitionInformation transitionInformation;
    
    public StageManager() {
        this.currentStage = new FirstEcho();
        this.islandEdgeManager = new IslandEdgeManager();
        this.currentTransition = new FirstEchoTransition();
        this.transitionInformation = new TransitionInformation();
    }

    public void setStage(Stage newStage) {
        this.currentStage = newStage;
    }

    public void setTransition(Transition newTransition) {
        this.currentTransition = newTransition;
    }

    public TransitionInformation getTransitionInfo() {
        return transitionInformation;
    }

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

                // String dec;
                
                
                
                // logger.info("** Current Direction: {}\n", direction.getDirection().stringForward());
                // if (previousState.getPrevAction() != null && previousState.getPrevAction().equals("scan")) {
                //     if (previousState.retrieveCreeks() != null) {
                //         for (int i = 0; i < previousState.retrieveCreeks().length; i++) {
                //             poiManager.addCreek(new Creek(this.coordinateManager.getCoordinates(), previousState.retrieveCreeks()[i]));
                //         }
                //     }

                //     if (previousState.retrieveSites() != null) {
                //         poiManager.setSiteLocation(new Site(this.coordinateManager.getCoordinates(), previousState.retrieveSites()[0]));
            
                //     }
                // }


                // if (battery.getBatteryLevel() - 10 <= 50) {
                //     previousState.setPrevAction("stop");
                //     dec = "stop";
                // }
                // else {
                //     dec = currentStage.makeDecision(direction, battery, previousState, this, poiManager, coordinateManager);
                // }       
                
            
                    
                // coordinateManager.adjustCoords(direction, previousState);

                // if (previousState.getPrevAction().equals("stop")) {
                //     logger.info("** Final battery level: {} **", battery.getBatteryLevel());
                //     logger.info("==== ALL ISLAND EDGES FOUND ====\nNorth: {}\nEast: {}\nSouth: {}\nWest: {}", poiManager.getIslandEdge("North").printCord(), poiManager.getIslandEdge("East").printCord(), poiManager.getIslandEdge("South").printCord(), poiManager.getIslandEdge("West").printCord());
                //     ArrayList<Creek> creeks = poiManager.getCreeks();
                //     logger.info("==== ALL CREEKS FOUND ====\n{}", poiManager.getCreeks());
                //     for (Creek creek : creeks) {
                //         logger.info("Creek: COORD:[{},{}], ID:{}", creek.getCreekCoordinate().getX().getCoordVal(), creek.getCreekCoordinate().getY().getCoordVal(), creek.getCreekID());
                //     }

                //     logger.info("==== EMERGENCY SITE FOUND ====\nSite: COORD:[{},{}], ID:{}", poiManager.getSite().getSiteCoordinate().getX().getCoordVal(), poiManager.getSite().getSiteCoordinate().getY().getCoordVal(), poiManager.getSite().getSiteID());
                    
                // }

                // return dec;
    }
}