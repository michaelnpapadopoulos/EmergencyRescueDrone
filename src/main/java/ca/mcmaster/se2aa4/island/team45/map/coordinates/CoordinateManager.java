package ca.mcmaster.se2aa4.island.team45.map.coordinates;

import ca.mcmaster.se2aa4.island.team45.drone.PreviousState;
import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;

public class CoordinateManager {
    private Coordinate currentCoords;
    private CoordinateAdjuster coordAdjuster = new CoordinateAdjuster();

    public CoordinateManager() {
        this.currentCoords = new Coordinate(new CoordValue(1), new CoordValue(1));
    }

    public void adjustCoords(DirectionManager dm, PreviousState ps) {
        this.currentCoords = coordAdjuster.makeAdjustment(dm, ps, this.currentCoords);
    }

    public Coordinate getCoordinates() {
        return new Coordinate(new CoordValue(getCoordinateX()), new CoordValue(getCoordinateY()));
    }

    public Coordinate getRearCoordinate(DirectionManager dm) {
        Coordinate rearAdjustedCoords = getCoordinates();
        switch (dm.getDirection().stringForward()) {
            case "N":
                rearAdjustedCoords.incrementY();
                break;

            case "E":
                rearAdjustedCoords.decrementX();
                break;

            case "S":
                rearAdjustedCoords.decrementY();
                break;

            case "W":
                rearAdjustedCoords.incrementX();
                break;

            default:
                return null;
        }
        
        return rearAdjustedCoords;
    }


    public int getCoordinateX() {
        return this.currentCoords.getX().getCoordVal();
    }

    public int getCoordinateY() {
        return this.currentCoords.getY().getCoordVal();
    }
}