package ca.mcmaster.se2aa4.island.team45.decision_stages;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.team45.decision_stages.finding_island.FirstEcho;
import ca.mcmaster.se2aa4.island.team45.drone.PreviousState;
import ca.mcmaster.se2aa4.island.team45.drone.battery.BatteryManager;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.map.coordinates.CoordinateManager;
import ca.mcmaster.se2aa4.island.team45.map.interest_points.Creek;
import ca.mcmaster.se2aa4.island.team45.map.interest_points.POIManager;
import ca.mcmaster.se2aa4.island.team45.map.interest_points.Site;

public class StageManager {
    private final Logger logger = LogManager.getLogger();

    private Stage currentStage;
    private CoordinateManager coordinateManager;

    public StageManager() {
        this.currentStage = new FirstEcho();
        this.coordinateManager = new CoordinateManager();
    }

    public void setStage(Stage newStage) {
        this.currentStage = newStage;
    }

    public String makeStageDecision(DirectionManager direction, BatteryManager battery, PreviousState previousState, POIManager poiManager) {
        String dec;
        
        logger.info("\n** Current Stage: {} **", currentStage.getClass().getSimpleName());
        logger.info("** Current Coords: {}", this.coordinateManager.getCoordinates().printCord());
        logger.info("** Current Direction: {}\n", direction.getDirection().stringForward());
        if (previousState.getPrevAction() != null && previousState.getPrevAction().equals("scan")) {
            logger.info("BIOMES: {}\nCREEKS: {}\nSITES: {}", previousState.getBiomes(), previousState.getCreeks(), previousState.getSites());
            // if (previousState.retrieveCreeks() != null) {
            //     for (int i = 0; i < previousState.retrieveCreeks().length; i++) {
            //         poiManager.addCreek(new Creek(this.coordinateManager.getCoordinates(), previousState.retrieveCreeks()[i]));
            //     }
            // }

            // if (previousState.retrieveSites() != null) {
            //     poiManager.setSiteLocation(new Site(this.coordinateManager.getCoordinates(), previousState.retrieveSites()[0]));
    
            // }
        }
        if (battery.getBatteryLevel() - 10 <= 50) {
            previousState.setPrevAction("stop");
            dec = "stop";
        }
        else {
            dec = currentStage.makeDecision(direction, battery, previousState, this, poiManager, coordinateManager);
        }       
        
       
            
        coordinateManager.adjustCoords(direction, previousState);

        if (previousState.getPrevAction().equals("stop")) {
            logger.info("** Final battery level: {} **", battery.getBatteryLevel());
            logger.info("==== ALL ISLAND EDGES FOUND ====\nNorth: {}\nEast: {}\nSouth: {}\nWest: {}", poiManager.getIslandEdge("North").printCord(), poiManager.getIslandEdge("East").printCord(), poiManager.getIslandEdge("South").printCord(), poiManager.getIslandEdge("West").printCord());
            // ArrayList<Creek> creeks = poiManager.getCreeks();
            // logger.info("==== ALL CREEKS FOUND ====\n{}", poiManager.getCreeks());
            // for (Creek creek : creeks) {
            //     logger.info("Creek: COORD:[{},{}], ID:{}", creek.getCreekCoordinate().getX().getCoordVal(), creek.getCreekCoordinate().getY().getCoordVal(), creek.getCreekID());
            // }

            // logger.info("==== EMERGENCY SITE FOUND ====\nSite: COORD:[{},{}], ID:{}", poiManager.getSite().getSiteCoordinate().getX().getCoordVal(), poiManager.getSite().getSiteCoordinate().getY().getCoordVal(), poiManager.getSite().getSiteID());
            
        }

        return dec;
    }
}