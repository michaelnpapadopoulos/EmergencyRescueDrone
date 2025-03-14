package ca.mcmaster.se2aa4.island.team45.missionphase;

import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.team45.drone.BatteryManager;
import ca.mcmaster.se2aa4.island.team45.drone.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.drone.PreviousResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class PhaseManager {
    private final Logger logger = LogManager.getLogger();
    private Phase currentPhase;
    private int counter = 0;
    private boolean movedForward = false;

    enum Phase {
        INITIAL, 
        FINDISLAND, 
        FINDCREEKS,
        FINDSITE, 
        STOP
    }

    public PhaseManager() {
        this.currentPhase = Phase.INITIAL;
    }

    public Phase getCurrentPhase() {
        return this.currentPhase;
    }

    public void nextPhase() {
        switch (this.currentPhase) {
            case INITIAL:
                this.currentPhase = Phase.FINDISLAND;
                break;
            case FINDISLAND:
                this.currentPhase = Phase.FINDCREEKS;
                break;
            case FINDCREEKS:
                this.currentPhase = Phase.FINDSITE;
                break;
            case FINDSITE:
                this.currentPhase = Phase.STOP;
                break;
            case STOP:
                break;
        }
    }

    public JSONObject makePhaseDecision(DirectionManager direction, BatteryManager battery, PreviousResult previousResult) {
        JSONObject decision = new JSONObject();
        logger.info("** Making decision in phase {}", this.currentPhase);

        if (this.currentPhase == Phase.INITIAL) { // Super rough draft of the initial phase and how phases will be structured. Will rework this.
            logger.info("Checking prevResults");
            if (previousResult.getExtras() != null) {
                logger.info("getExtras not null");
                JSONObject extras = previousResult.getExtras();
                if (extras.has("found")) {
                    if (extras.getString("found").equals("GROUND")) {
                        decision.put("action", "stop");
                        return decision;
                    }
                } 
            }

            logger.info("Checking counter");
            if (this.counter < 5) {
                logger.info("Checking movedForward");
                if (movedForward == false) {
                    decision.put("action", "fly");
                    movedForward = true;
                    counter++;
                } else {
                    decision.put("action", "scan");
                    movedForward = false;
                }
            } else {
                counter = 0;
                decision.put("action", "echo");

                JSONObject param = new JSONObject();
                param.put("direction", "S");
                decision.put("parameters", param);
            }
            
            return decision;
        } else {
            decision.put("action", "stop");
            return decision;
        }
    }
}
