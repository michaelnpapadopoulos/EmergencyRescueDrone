package ca.mcmaster.se2aa4.island.team45.map.coordinates;

import ca.mcmaster.se2aa4.island.team45.decision_stages.PreviousDecision;
import ca.mcmaster.se2aa4.island.team45.drone.DirectionManager;

public class CoordinateAdjuster {

    public Coordinate makeAdjustment(DirectionManager dm, PreviousDecision pd, Coordinate coordinates) {

        if (pd.getPrevAction().equals("fly")) {
            return flyAdjust(dm, coordinates);
        } else if (pd.getPrevAction().equals("heading")) {
            return headingAdjust(dm, pd, coordinates);
        } else {
            return coordinates;
        }
    }

    private Coordinate flyAdjust(DirectionManager dm, Coordinate coords) {
        Coordinate newCoords = coords;
        switch (dm.getDirection()) {
            case "N":
                newCoords.decrementY();
                break;
            case "E":
                newCoords.incrementX();
                break;
            case "S":
                newCoords.incrementY();
                break;
            case "W":
                newCoords.decrementX();
                break;
        }
        return newCoords;
    }

    private Coordinate headingAdjust(DirectionManager dm, PreviousDecision pd, Coordinate coords) {
        Coordinate newCoords = coords;
        switch (dm.getDirection()) {
            case "N":
                if (pd.getPrevHeading().equals("E")) {
                    newCoords.incrementX();
                } else if (pd.getPrevHeading().equals("W")) {
                    newCoords.decrementX();
                }
                newCoords.decrementY();
                break;

            case "E":
                if (pd.getPrevHeading().equals("N")) {
                    newCoords.decrementY();
                } else if (pd.getPrevHeading().equals("S")) {
                    newCoords.incrementY();
                }
                newCoords.incrementX();
                break;

            case "S":
                if (pd.getPrevHeading().equals("E")) {
                    newCoords.decrementX();
                } else if (pd.getPrevHeading().equals("W")) {
                    newCoords.decrementX();
                }
                newCoords.incrementY();
                break;

            case "W":
                if (pd.getPrevHeading().equals("N")) {
                    newCoords.decrementY();
                } else if (pd.getPrevHeading().equals("S")) {
                    newCoords.incrementY();
                }
                newCoords.decrementX();
                break;
        }
        
        dm.setDirection(pd.getPrevHeading());
        return newCoords;
    }
}
