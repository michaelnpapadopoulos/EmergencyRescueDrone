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
        switch (direction.toString()) {
            case "N":
                return new Direction("S");
            case "E":
                return new Direction("W");
            case "S":
                return new Direction("N");
            case "W":
                return new Direction("E");
            default:
                return null;
        }
    }


}
