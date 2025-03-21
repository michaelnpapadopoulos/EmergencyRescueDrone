package ca.mcmaster.se2aa4.island.team45.drone;

public class DirectionManager {
    private DirectionState directionState;

    public DirectionManager() {
        this.directionState = new NorthDirectionState(); // Default state NOTE THIS IS AN ASSUMPTION I MADE BEFORE YOU CREATED A DYNAMIC WAY TO MOVE THE DRONE FROM ANY DIRECTION
    }

    public void setDirection(String direction) {
        switch (direction) {
            case "N":
                this.directionState = new NorthDirectionState();
                break;
            case "E":
                this.directionState = new EastDirectionState();
                break;
            case "S":
                this.directionState = new SouthDirectionState();
                break;
            case "W":
                this.directionState = new WestDirectionState();
                break;
        }
    }

    public String getLeft() {
        return directionState.getLeft();
    }

    public String getRight() {
        return directionState.getRight();
    }

    public String getDirection() {
        return directionState.getDirection();
    }
}