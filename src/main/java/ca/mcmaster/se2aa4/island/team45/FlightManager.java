package ca.mcmaster.se2aa4.island.team45;

public class FlightManager {
    private static final FlightManager fm = new FlightManager();
    private DirectionManager dm;
    private BatteryManager bm;

    private FlightManager() {}

    public void passInitialInfo(String direction, int batteryLevel) {
        dm = new DirectionManager(direction);
        bm = new BatteryManager(batteryLevel);
    }
    
    public static FlightManager getInstance() {
        return fm;
    }
}
