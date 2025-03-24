package ca.mcmaster.se2aa4.island.team45;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.island.team45.drone.commands.CommandCenter;

public class CommandCenterTest {

    private CommandCenter commandCenter;

    @BeforeEach
    public void setUp() {
        commandCenter = new CommandCenter();
    }

    @Test
    public void testMakeActionWithHeading() {
        String action = commandCenter.makeAction("heading", "N");
        assertNotNull(action);
        assertTrue(action.contains("heading"));
    }

    @Test
    public void testMakeActionWithoutHeading() {
        String action = commandCenter.makeAction("fly");
        assertNotNull(action);
        assertTrue(action.contains("fly"));
    }

    @Test
    public void testWasPrevAction() {
        commandCenter.makeAction("fly");
        assertTrue(commandCenter.wasPrevAction("fly"));
    }
}