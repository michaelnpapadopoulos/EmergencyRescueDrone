package ca.mcmaster.se2aa4.island.team45.decision_stages;

import ca.mcmaster.se2aa4.island.team45.drone.BatteryManager;
import ca.mcmaster.se2aa4.island.team45.drone.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.drone.PreviousResult;

public class StageManager {
    private Stage currentStage;
    private PreviousDecision previousDecision;

    public StageManager() {
        this.currentStage = new InitialStage();
        this.previousDecision = new PreviousDecision();
    }

    public void setStage(Stage newStage) {
        this.currentStage = newStage;
    }

    public String makeStageDecision(DirectionManager direction, BatteryManager battery, PreviousResult previousResult, StageManager stageManager) {
       return currentStage.makeDecision(direction, battery, previousResult, this.previousDecision, this);
    }
}
