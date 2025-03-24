package ca.mcmaster.se2aa4.island.team45.map.coordinates;

import ca.mcmaster.se2aa4.island.team45.drone.commands.CommandCenter;
import ca.mcmaster.se2aa4.island.team45.drone.direction.*;

public class CoordinateUtilities {

    private CoordinateUtilities() {}

    public static Coordinate getShiftedCoordinates(Coordinate coordinate, Direction direction, int shiftValue) {
        int coordinateX = coordinate.getX();
        int coordinateY = coordinate.getY();
        
        switch (direction.toString()) {
            case "N":
                return new Coordinate(coordinateX, coordinateY - shiftValue);

            case "E":
                return new Coordinate(coordinateX + shiftValue, coordinateY);

            case "S":
                return new Coordinate(coordinateX, coordinateY + shiftValue);

            case "W":
                return new Coordinate(coordinateX - shiftValue, coordinateY);

                default:
                return null;
        }
    }

    public static Coordinate makeAdjustment(DirectionManager directionManager, CommandCenter commandCenter, Coordinate coordinates) {
        if (commandCenter.wasPrevAction("fly")) { // If previous action was fly, adjust coordinates
            return flyAdjust(directionManager, coordinates);
        } else if (commandCenter.wasPrevAction("heading")) { // If previous action was heading, adjust coordinates
            return headingAdjust(directionManager, commandCenter, coordinates);
        } else { // If previous action was not fly or heading, return coordinates unchanged
            return coordinates;
        }
    }

    private static Coordinate flyAdjust(DirectionManager directionManager, Coordinate coords) {
        switch (directionManager.getDirection().toString()) {
            case "N":
                return new Coordinate(coords.getX(), coords.getY() - 1);

            case "E":
                return new Coordinate(coords.getX() + 1, coords.getY());
          
            case "S":
                return new Coordinate(coords.getX(), coords.getY() + 1);
         
            case "W":
                return new Coordinate(coords.getX() - 1, coords.getY());
          
            default:
                return coords;
        }
    }

    private static Coordinate headingAdjust(DirectionManager directionManager, CommandCenter commandCenter, Coordinate coords) {
        switch (directionManager.getDirection().toString()) { // Based on the current direction of the plane, adjust the coordinates
            case "N":
                if (commandCenter.wasPrevHeading("E")) { // If just turned east, increment x
                    return new Coordinate(coords.getX() + 1, coords.getY() - 1);
                } else if (commandCenter.wasPrevHeading("W")) { // If just turned west, decrement x
                    return new Coordinate(coords.getX() - 1, coords.getY() - 1);
                }
                break;

            case "E":
                if (commandCenter.wasPrevHeading("N")) { // If just turned north, decrement y
                    return new Coordinate(coords.getX() + 1, coords.getY() - 1);
                } else if (commandCenter.wasPrevHeading("S")) { // If just turned south, increment y
                    return new Coordinate(coords.getX() + 1, coords.getY() + 1);
                }
                break;

            case "S":
                if (commandCenter.wasPrevHeading("E")) { // If just turned east, increment x
                    return new Coordinate(coords.getX() + 1, coords.getY() + 1);
                } else if (commandCenter.wasPrevHeading("W")) { // If just turned west, decrement x
                    return new Coordinate(coords.getX() - 1, coords.getY() + 1);
                }
                break;

            case "W":
                if (commandCenter.wasPrevHeading("N")) { // If just turned north, decrement y
                    return new Coordinate(coords.getX() - 1, coords.getY() - 1);
                } else if (commandCenter.wasPrevHeading("S")) { // If just turned south, increment y
                    return new Coordinate(coords.getX() - 1, coords.getY() + 1);
                }
                break;
        }
        return coords;
    }
}
