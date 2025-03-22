package ca.mcmaster.se2aa4.island.team45.map.coordinates;

import ca.mcmaster.se2aa4.island.team45.drone.commands.CommandCenter;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;

public class CoordinateAdjuster {

    public Coordinate makeAdjustment(DirectionManager directionManager, CommandCenter commandCenter, Coordinate coordinates) {
        if (commandCenter.getPrevAction().equals("fly")) {
            return flyAdjust(directionManager, coordinates);
        } else if (commandCenter.getPrevAction().equals("heading")) {
            return headingAdjust(directionManager, commandCenter, coordinates);
        } else {
            return coordinates;
        }
    }

    private Coordinate flyAdjust(DirectionManager directionManager, Coordinate coords) {
        Coordinate newCoords = coords;
        switch (directionManager.getDirection().stringForward()) {
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

    private Coordinate headingAdjust(DirectionManager directionManager, CommandCenter commandCenter, Coordinate coords) {
        Coordinate newCoords = coords;
        switch (directionManager.getDirection().stringForward()) {
            case "N":
                if (commandCenter.getPrevHeading().equals("E")) {
                    newCoords.incrementX();
                } else if (commandCenter.getPrevHeading().equals("W")) {
                    newCoords.decrementX();
                }
                newCoords.decrementY();
                break;

            case "E":
                if (commandCenter.getPrevHeading().equals("N")) {
                    newCoords.decrementY();
                } else if (commandCenter.getPrevHeading().equals("S")) {
                    newCoords.incrementY();
                }
                newCoords.incrementX();
                break;

            case "S":
                if (commandCenter.getPrevHeading().equals("E")) {
                    newCoords.incrementX();
                } else if (commandCenter.getPrevHeading().equals("W")) {
                    newCoords.decrementX();
                }
                newCoords.incrementY();
                break;

            case "W":
                if (commandCenter.getPrevHeading().equals("N")) {
                    newCoords.decrementY();
                } else if (commandCenter.getPrevHeading().equals("S")) {
                    newCoords.incrementY();
                }
                newCoords.decrementX();
                break;
        }
        
        return newCoords;
    }
}
