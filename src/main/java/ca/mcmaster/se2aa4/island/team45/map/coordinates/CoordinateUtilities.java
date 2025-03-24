package ca.mcmaster.se2aa4.island.team45.map.coordinates;

import ca.mcmaster.se2aa4.island.team45.drone.commands.CommandCenter;
import ca.mcmaster.se2aa4.island.team45.drone.direction.*;

public class CoordinateUtilities {

    /**************************************************************************
     * Coordinate utilities constructor
    **************************************************************************/
    private CoordinateUtilities() {}

    /**************************************************************************
     * Returns a coordinate shifted by a value in a direction from your current
     * coordinates
     * 
     * @param coordinate the drones current coordinate
     * @param direction the direction you want to shift
     * @param shiftValue the shift you want to make from your coordinates
    **************************************************************************/
    public static Coordinate getShiftedCoordinates(Coordinate coordinate, Direction direction, int shiftValue) {
        int coordinateX = coordinate.getX();
        int coordinateY = coordinate.getY();

        return switch (direction.toString()) {
            case "N" -> new Coordinate(coordinateX, coordinateY - shiftValue);
            case "E" -> new Coordinate(coordinateX + shiftValue, coordinateY);
            case "S" -> new Coordinate(coordinateX, coordinateY + shiftValue);
            case "W" -> new Coordinate(coordinateX - shiftValue, coordinateY);
            default -> null;
        };
    }

    /**************************************************************************
     * Makes adjustments to your drones coordinates depending on what action
     * you take
     * 
     * @param directionManager the drones direction manager
     * @param commandCenter the drones command center
     * @param coordinates the drones current coordinates
    **************************************************************************/
    public static Coordinate makeAdjustment(DirectionManager directionManager, CommandCenter commandCenter, Coordinate coordinates) {
        if (commandCenter.wasPrevAction("fly")) { // If previous action was fly, adjust coordinates
            return flyAdjust(directionManager, coordinates);
        } else if (commandCenter.wasPrevAction("heading")) { // If previous action was heading, adjust coordinates
            return headingAdjust(directionManager, commandCenter, coordinates);
        } else { // If previous action was not fly or heading, return coordinates unchanged
            return coordinates;
        }
    }

    /**************************************************************************
     * Adjusts your coordinates when you take the fly action
     * 
     * @param directionManager the drones direction manager
     * @param coords the drones current coordinates
    **************************************************************************/
    private static Coordinate flyAdjust(DirectionManager directionManager, Coordinate coords) {
        return switch (directionManager.getDirection().toString()) {
            case "N" -> new Coordinate(coords.getX(), coords.getY() - 1);
            case "E" -> new Coordinate(coords.getX() + 1, coords.getY());
            case "S" -> new Coordinate(coords.getX(), coords.getY() + 1);
            case "W" -> new Coordinate(coords.getX() - 1, coords.getY());
            default -> coords;
        };
    }

    /**************************************************************************
     * Adjusts your coordinates when you take the heading action
     * 
     * @param directionManager the drones direction manager
     * @param commandCenter the drones command center
     * @param coords the drones current coordinates
    **************************************************************************/
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
