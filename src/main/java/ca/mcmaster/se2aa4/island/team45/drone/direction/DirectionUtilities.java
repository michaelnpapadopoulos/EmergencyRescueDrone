package ca.mcmaster.se2aa4.island.team45.drone.direction;

public class DirectionUtilities {

    /**************************************************************************
     * Direction Utilities constructor
    **************************************************************************/
    private DirectionUtilities() {}

    /**************************************************************************
     * Gets the opposite direction of a given direction
     *
     * @param direction a cardinal direction (N,S,E,W)
    **************************************************************************/
    public static Direction getOpposite(Direction direction) {
        return switch (direction.toString()) {
            case "N" -> new Direction("S");
            case "E" -> new Direction("W");
            case "S" -> new Direction("N");
            case "W" -> new Direction("E");
            default -> null;
        };
    }


}
