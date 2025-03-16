package ca.mcmaster.se2aa4.island.team45.map;

import ca.mcmaster.se2aa4.island.team45.decision_stages.PreviousDecision;
import ca.mcmaster.se2aa4.island.team45.drone.DirectionManager;

public class CoordinateAdjuster {

    public int[] makeAdjustment(DirectionManager dm, PreviousDecision pd, int[] coordinates) {

        if (pd.getPrevAction().equals("fly")) {
            return flyAdjust(dm, coordinates);
        } else if (pd.getPrevAction().equals("heading")) {
            return headingAdjust(dm, pd, coordinates);
        } else {
            return coordinates;
        }
    }

    private int[] flyAdjust(DirectionManager dm, int[] coords) {
        int[] newCoords = coords;
        switch (dm.getDirection()) {
            case "N":
                newCoords[1]--;
                break;
            case "E":
                newCoords[0]++;
                break;
            case "S":
                newCoords[1]++;
                break;
            case "W":
                newCoords[0]--;
                break;
        }

        return newCoords;
    }

    private int[] headingAdjust(DirectionManager dm, PreviousDecision pd, int[] coords) {
        int[] newCoords = coords;
        switch (dm.getDirection()) {
            case "N":
                if (pd.getPrevHeading().equals("E")) {
                    newCoords[0]++;
                } else if (pd.getPrevHeading().equals("W")) {
                    newCoords[0]--;
                }
                newCoords[1]--;
                break;

            case "E":
                if (pd.getPrevHeading().equals("N")) {
                    newCoords[1]--;
                } else if (pd.getPrevHeading().equals("S")) {
                    newCoords[1]++;
                }
                newCoords[0]++;
                break;

            case "S":
                if (pd.getPrevHeading().equals("E")) {
                    newCoords[0]++;
                } else if (pd.getPrevHeading().equals("W")) {
                    newCoords[0]--;
                }
                newCoords[1]++;
                break;

            case "W":
                if (pd.getPrevHeading().equals("N")) {
                    newCoords[1]--;
                } else if (pd.getPrevHeading().equals("S")) {
                    newCoords[1]++;
                }
                newCoords[0]--;
                break;
        }

        dm.setDirection(pd.getPrevHeading());
        return newCoords;
    }
}
