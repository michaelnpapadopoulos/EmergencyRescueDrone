package ca.mcmaster.se2aa4.island.team45;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.island.team45.drone.FlightCommands;

public class FlightCommandsTest {

    @Test
    public void testFly() {
        FlightCommands commands = FlightCommands.getInstance();
        assertEquals("{\"action\":\"fly\"}", commands.fly());
    }

    @Test
    public void testHeading() {
        FlightCommands commands = FlightCommands.getInstance();
        assertEquals("{\"action\":\"heading\",\"parameters\":{\"direction\":\"N\"}}", commands.heading("N"));
    }

    @Test
    public void testEcho() {
        FlightCommands commands = FlightCommands.getInstance();
        assertEquals("{\"action\":\"echo\",\"parameters\":{\"direction\":\"N\"}}", commands.echo("N"));
    }
}