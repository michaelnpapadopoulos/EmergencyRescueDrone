package ca.mcmaster.se2aa4.island.team45.drone.drone_observers;

import ca.mcmaster.se2aa4.island.team45.drone.*;
import ca.mcmaster.se2aa4.island.team45.drone.commands.CommandCenter;
import ca.mcmaster.se2aa4.island.team45.map.interest_points.*;
import ca.mcmaster.se2aa4.island.team45.map.coordinates.*;

public class POIObserver implements StatusObserver {
    
    /**************************************************************************
     * Updates the poiManager with emergency sites and creeks
     *
     * @param droneStatus tracks the drones current attributes
     * @param previousResult tracks the JSONObject created from the last action
     * @param poiManager used to update the points of interest found during 
     * scan
    **************************************************************************/
    @Override
    public void updateStatus(DroneStatus droneStatus, PreviousResult previousResult, POIManager poiManager) {
        CommandCenter commandCenter = droneStatus.getCommandCenter();
        CoordinateManager coordinateManager = droneStatus.getCoordinateManager();
        
        if (commandCenter.wasPrevAction("scan")) {
            Coordinate poiCoords = coordinateManager.getCoordinates();
            String[] siteList = previousResult.retrieveSites();
            String[] creekList = previousResult.retrieveCreeks();

            if (siteList != null) {
                Site emergencySite = new Site(poiCoords, siteList[0]);
                poiManager.setSiteLocation(emergencySite);
            }

            if (creekList != null) {
                for (String creek : creekList) {
                    Creek creekObj = new Creek(poiCoords, creek);
                    poiManager.addCreek(creekObj);
                }
            }
        }
    }
    
}
