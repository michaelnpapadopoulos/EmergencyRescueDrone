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
    public void turnLeft() { 
        this.trueDirection = (this.trueDirection+3)%4; 
        setDirection();
    } 

    public void turnRight() { 
        this.trueDirection = (this.trueDirection+1)%4;
        setDirection(); 
    }

    private void setDirection() { // Sets the direction based on the trueDirection value
        switch (this.trueDirection) {
            case 0:
                this.direction = 'N';
                break;
            case 1:
                this.direction = 'E';
                break;
            case 2:
                this.direction = 'S';
                break;
            case 3:
                this.direction = 'W';
                break;
        }
    }

    public char getDirection() { return this.direction; } // Getter for the direction
}
