package ca.mcmaster.se2aa4.island.team45.decision_stages;

import ca.mcmaster.se2aa4.island.team45.drone.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.drone.BatteryManager;
import ca.mcmaster.se2aa4.island.team45.drone.PreviousResult;

public abstract class Stage {
    public abstract String makeDecision(DirectionManager direction, BatteryManager battery, PreviousResult previousResult, PreviousDecision previousDecision, StageManager stageManager);
}
