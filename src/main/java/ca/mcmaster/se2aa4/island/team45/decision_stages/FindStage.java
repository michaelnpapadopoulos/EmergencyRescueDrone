package ca.mcmaster.se2aa4.island.team45.decision_stages;

import ca.mcmaster.se2aa4.island.team45.drone.BatteryManager;
import ca.mcmaster.se2aa4.island.team45.drone.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.drone.FlightCommands;
import ca.mcmaster.se2aa4.island.team45.drone.PreviousResult;

public class FindStage extends Stage { // Get to the edge of the islands sqhare perimeter

    public String makeDecision(DirectionManager direction, BatteryManager battery, PreviousResult pResult, PreviousDecision pDecision, StageManager sm) {
        if (pDecision.getPrevAction().equals("heading")) {
            pDecision.setPrevAction("scan");
            return FlightCommands.getInstance().scan();
        }

        return FlightCommands.getInstance().stop();
    }

}
