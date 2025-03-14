package ca.mcmaster.se2aa4.island.team45.drone;

import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.team45.missionphase.PhaseManager;

public class DecisionManager { // Makes decisions based on the current state of the drone
    private static final DecisionManager dm = new DecisionManager();
    private final PhaseManager phaseManager;

    private DecisionManager() {
        this.phaseManager = new PhaseManager();
    }

    public static DecisionManager getInstance() {
        return dm;
    }

    public JSONObject makeDecision(DirectionManager direction, BatteryManager battery, PreviousResult previousResult) {
        return this.phaseManager.makePhaseDecision(direction, battery, previousResult);
    }
    
}