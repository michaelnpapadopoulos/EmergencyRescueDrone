package ca.mcmaster.se2aa4.island.team45.map.coordinates;

import ca.mcmaster.se2aa4.island.team45.drone.PreviousState;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;

public class CoordinateAdjuster {

    public Coordinate makeAdjustment(DirectionManager dm, PreviousState ps, Coordinate coordinates) {

        if (ps.getPrevAction().equals("fly")) {
            return flyAdjust(dm, coordinates);
        } else if (ps.getPrevAction().equals("heading")) {
            return headingAdjust(dm, ps, coordinates);
        } else {
            return coordinates;
        }
    }

    private Coordinate flyAdjust(DirectionManager dm, Coordinate coords) {
        Coordinate newCoords = coords;
        switch (dm.getDirection().stringForward()) {
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

    private Coordinate headingAdjust(DirectionManager dm, PreviousState ps, Coordinate coords) {
        Coordinate newCoords = coords;
        switch (dm.getDirection().stringForward()) {
            case "N":
                if (ps.getPrevHeading().equals("E")) {
                    newCoords.incrementX();
                } else if (ps.getPrevHeading().equals("W")) {
                    newCoords.decrementX();
                }
                newCoords.decrementY();
                break;

            case "E":
                if (ps.getPrevHeading().equals("N")) {
                    newCoords.decrementY();
                } else if (ps.getPrevHeading().equals("S")) {
                    newCoords.incrementY();
                }
                newCoords.incrementX();
                break;

            case "S":
                if (ps.getPrevHeading().equals("E")) {
                    newCoords.incrementX();
                } else if (ps.getPrevHeading().equals("W")) {
                    newCoords.decrementX();
                }
                newCoords.incrementY();
                break;

            case "W":
                if (ps.getPrevHeading().equals("N")) {
                    newCoords.decrementY();
                } else if (ps.getPrevHeading().equals("S")) {
                    newCoords.incrementY();
                }
                newCoords.decrementX();
                break;
        }
        
        dm.setDirection(ps.getPrevHeading());
        return newCoords;
    }
}
