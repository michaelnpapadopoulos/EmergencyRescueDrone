package ca.mcmaster.se2aa4.island.team45;
import org.json.JSONObject;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.island.team45.drone.PreviousState;

public class PreviousStateTest {

    @Test
    public void testSetPreviousResult() {
        PreviousState state = new PreviousState();
        JSONObject extras = new JSONObject();
        extras.put("found", "creek");
        state.setPreviousResult(10, "success", extras);
        assertEquals(10, state.getCost());
        assertEquals("success", state.getStatus());
        assertEquals("creek", state.getFound());
    }
}