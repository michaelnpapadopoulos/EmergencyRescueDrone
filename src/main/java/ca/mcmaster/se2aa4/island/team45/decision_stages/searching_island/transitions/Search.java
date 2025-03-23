package ca.mcmaster.se2aa4.island.team45.decision_stages.searching_island.transitions;

import org.json.JSONArray;
import ca.mcmaster.se2aa4.island.team45.drone.commands.PreviousResult;

public interface Search {
    static boolean overWater(PreviousResult previousResult) {
        JSONArray biomes = previousResult.getBiomes();
        if (biomes == null) {
            return false;
        }

        boolean waterPresent = false;
        boolean landPresent = false;
        for (int i = 0; i < biomes.length(); i++) {
            if (biomes.getString(i).equals("OCEAN") || biomes.getString(i).equals("LAKE")) {
                waterPresent = true;
            }

            if (!biomes.getString(i).equals("OCEAN") && !biomes.getString(i).equals("LAKE")) {
                landPresent = true;
            }
        }

        if (waterPresent && !landPresent) {
            return true;
        }

        return false;
    }
}
