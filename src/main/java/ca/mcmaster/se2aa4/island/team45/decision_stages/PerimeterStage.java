package ca.mcmaster.se2aa4.island.team45.decision_stages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.team45.drone.BatteryManager;
import ca.mcmaster.se2aa4.island.team45.drone.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.drone.FlightCommands;
import ca.mcmaster.se2aa4.island.team45.drone.PreviousResult;
import ca.mcmaster.se2aa4.island.team45.map.CoordinateManager;
import ca.mcmaster.se2aa4.island.team45.map.IslandEdgeManager;
import ca.mcmaster.se2aa4.island.team45.map.POIManager;

public class PerimeterStage extends Stage {
    private final Logger logger = LogManager.getLogger();
    private IslandEdgeManager islandEdgeManager;

    public PerimeterStage() {
        this.islandEdgeManager = new IslandEdgeManager();
    }

    public String makeDecision(DirectionManager direction, BatteryManager battery, PreviousResult pResult, PreviousDecision pDecision, StageManager sm, POIManager poiManager, CoordinateManager cm) {
        logger.info("Making mission stage decision...");

        if (pDecision.getPrevAction() == null || pDecision.getPrevAction().equals("fly")) {
            pDecision.setPrevAction("echo");
            pDecision.setPrevHeading(direction.getRight());
            return FlightCommands.getInstance().echo(direction.getRight());
        } else if (pResult.getFound() != null && pResult.getFound().equals("GROUND") && !islandEdgeManager.hasEdge("West")) {
            logger.info("Found West edge of island");
            pDecision.setPrevAction("scan");

            islandEdgeManager.addEdge("West", new int[] {cm.getX(), -1});
            poiManager.addIslandEdge("West", new int[] {cm.getX(), -1});

            return FlightCommands.getInstance().scan();
        } else if (pResult.getFound() != null && pResult.getFound().equals("OUT_OF_RANGE") && !islandEdgeManager.hasEdge("East") && islandEdgeManager.hasEdge("West")) {
            logger.info("Found East edge of island");
            pDecision.setPrevAction("heading");
            pDecision.setPrevHeading(direction.getRight());

            islandEdgeManager.addEdge("East", new int[] {cm.getX() - 1, -1});
            poiManager.addIslandEdge("East", new int[] {cm.getX() - 1, -1});

            return FlightCommands.getInstance().heading(direction.getRight());
        } else if (pResult.getFound() != null && pResult.getFound().equals("GROUND") && !islandEdgeManager.hasEdge("North") && islandEdgeManager.hasEdge("East")) {
            logger.info("Found North edge of island");
            pDecision.setPrevAction("fly");

            islandEdgeManager.addEdge("North", new int[] {-1, cm.getY()});
            poiManager.addIslandEdge("North", new int[] {-1, cm.getY()});

            return FlightCommands.getInstance().fly();
        } else if (pResult.getFound() != null && pResult.getFound().equals("OUT_OF_RANGE") && !islandEdgeManager.hasEdge("South") && islandEdgeManager.hasEdge("North")) {
            logger.info("Found South edge of island");
            pDecision.setPrevAction("scan");

            islandEdgeManager.addEdge("South", new int[] {-1, cm.getY() - 1});
            poiManager.addIslandEdge("South", new int[] {-1, cm.getY() - 1});

            sm.setStage(new POIStage());
            return FlightCommands.getInstance().scan();
        } else {
            pDecision.setPrevAction("fly");
            return FlightCommands.getInstance().fly();
        }
    }

    
}