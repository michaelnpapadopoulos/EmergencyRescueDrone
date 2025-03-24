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
    
    /**************************************************************************
     * Constructs a drone status object
     *
     * @param batteryManager used to update the drones battery
     * @param coordinateManager used to update the drones coordinates
     * @param directionManager used to update the drones direction
     * @param commanCenter used to access previous decisions as well as drone 
     * commands
    **************************************************************************/
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

    /**************************************************************************
     *Returns the battery manager
    **************************************************************************/
    public BatteryManager getBatteryManager() {
        return batteryManager;
    }

    /**************************************************************************
     * Returns the coordinate Manager
    **************************************************************************/
    public CoordinateManager getCoordinateManager() {
        return coordinateManager;
    }

    /**************************************************************************
     * Returns the direction manager
    **************************************************************************/
    public DirectionManager getDirectionManager() {
        return directionManager;
    }

    /**************************************************************************
     * Returns the drones command center (previous decision, and commands)
    **************************************************************************/
    public CommandCenter getCommandCenter() {
        return commandCenter;
    }
}
