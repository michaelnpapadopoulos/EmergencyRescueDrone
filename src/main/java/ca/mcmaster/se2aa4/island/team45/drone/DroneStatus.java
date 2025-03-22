package ca.mcmaster.se2aa4.island.team45.drone;

import ca.mcmaster.se2aa4.island.team45.drone.battery.BatteryManager;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;
import ca.mcmaster.se2aa4.island.team45.map.coordinates.CoordinateManager;
import ca.mcmaster.se2aa4.island.team45.drone.commands.CommandCenter;

public class DroneStatus {
    private final BatteryManager batteryManager;
    private final CoordinateManager coordinateManager;
    private final DirectionManager directionManager;
    private final CommandCenter commandCenter;
    
    public DroneStatus(
        BatteryManager batteryManager,
        CoordinateManager coordinateManager,
        DirectionManager directionManager,
        CommandCenter commandCenter) {
            this.batteryManager = batteryManager;
            this.coordinateManager = coordinateManager;
            this.directionManager = directionManager;
            this.commandCenter = commandCenter;
    }

    public BatteryManager getBatteryManager() {
        return batteryManager;
    }

    public CoordinateManager getCoordinateManager() {
        return coordinateManager;
    }

    public DirectionManager getDirectionManager() {
        return directionManager;
    }

    public CommandCenter getCommandCenter() {
        return commandCenter;
    }
}
