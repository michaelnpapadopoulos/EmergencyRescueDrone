package ca.mcmaster.se2aa4.island.team45;

import org.json.JSONObject;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.island.team45.drone.PreviousResult;

public class PreviousResultTest {

    private PreviousResult previousResult;

    @BeforeEach
    public void setUp() {
        previousResult = new PreviousResult();
    }

    @Test
    public void testSetPreviousResult() {
        JSONObject extras = new JSONObject();
        previousResult.setPreviousResult(10, "success", extras);
        assertEquals(10, previousResult.getCost());
        assertEquals("success", previousResult.getStatus());
        assertEquals(extras, previousResult.getExtrasCopy());
    }

    @Test
    public void testGetFound() {
        JSONObject extras = new JSONObject();
        extras.put("found", "GROUND");
        previousResult.setPreviousResult(10, "success", extras);
        assertTrue(previousResult.getFound("GROUND"));
    }
}