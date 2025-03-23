package ca.mcmaster.se2aa4.island.team45.drone.drone_observers;


import ca.mcmaster.se2aa4.island.team45.map.coordinates.Coordinate;
import ca.mcmaster.se2aa4.island.team45.drone.DroneStatus;
import ca.mcmaster.se2aa4.island.team45.drone.PreviousResult;
import ca.mcmaster.se2aa4.island.team45.map.interest_points.Creek;
import ca.mcmaster.se2aa4.island.team45.map.interest_points.POIManager;
import ca.mcmaster.se2aa4.island.team45.map.interest_points.Site;

public class POIObserver implements StatusObserver {
    @Override
    public void updateStatus(DroneStatus droneStatus, PreviousResult previousResult, POIManager poiManager) {
        if (droneStatus.getCommandCenter().getPrevAction().equals("scan")) {
            Coordinate poiCoords = droneStatus.getCoordinateManager().getCoordinates();
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
