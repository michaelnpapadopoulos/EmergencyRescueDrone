package ca.mcmaster.se2aa4.island.team45.drone;

public class DirectionManager { // Keeps track of direction during path finding

    //=========== DIRECTION ATTRIBUTES ===========//
    private int trueDirection; // Stores a value between 0 and 3, 0 being North, 1 being east, and so on.
    private char direction;

    //=========== DIRECTION CONSTRUCTORS ===========//
    public DirectionManager(char startingDirection) { 
        this.direction = startingDirection; 
        switch (startingDirection) {
            case 'N':
                this.trueDirection = 0;
                break;
            case 'E':
                this.trueDirection = 1;
                break;
            case 'S':
                this.trueDirection = 2;
                break;
            case 'W':
                this.trueDirection = 3;
                break;
        }
    }
    
    //=========== DIRECTION METHODS ===========//
    public char getLeft() { 
        int turnedTrue = (this.trueDirection+3)%4; 
        return getCardinalDirection(turnedTrue);
    } 

    public char getRight() { 
        int turnedTrue = (this.trueDirection+1)%4;
        return getCardinalDirection(turnedTrue);
    }

    private char getCardinalDirection(int trueDirValue) { // Sets the direction based on the trueDirection value
        switch (trueDirValue) {
            case 0:
                return 'N';
            case 1:
                return 'E';
            case 2:
                return 'S';
            case 3:
                return 'W';
        }

        return ' ';
    }

    public char getDirection() { return this.direction; } // Getter for the direction

    public void setDirection(char newDirection) { // Setter for the direction
        this.direction = newDirection;
        switch (newDirection) {
            case 'N':
                this.trueDirection = 0;
                break;
            case 'E':
                this.trueDirection = 1;
                break;
            case 'S':
                this.trueDirection = 2;
                break;
            case 'W':
                this.trueDirection = 3;
                break;
        }
    }
}
