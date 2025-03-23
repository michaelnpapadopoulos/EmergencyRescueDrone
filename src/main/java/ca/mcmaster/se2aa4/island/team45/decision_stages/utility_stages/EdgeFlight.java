// package ca.mcmaster.se2aa4.island.team45.decision_stages.utility_stages;

// import ca.mcmaster.se2aa4.island.team45.decision_stages.Stage;
// import ca.mcmaster.se2aa4.island.team45.decision_stages.StageManager;
// import ca.mcmaster.se2aa4.island.team45.drone.battery.BatteryManager;
// import ca.mcmaster.se2aa4.island.team45.drone.commands.FlightCommands;
// import ca.mcmaster.se2aa4.island.team45.drone.commands.CommandCenter;
// import ca.mcmaster.se2aa4.island.team45.drone.direction.DirectionManager;
// import ca.mcmaster.se2aa4.island.team45.map.coordinates.CoordinateManager;
// import ca.mcmaster.se2aa4.island.team45.map.interest_points.POIManager;

// import org.apache.logging.log4j.LogManager;
// import org.apache.logging.log4j.Logger;
// import org.json.JSONArray;

// public class EdgeFlight implements Stage { // The initial stage of the mission, find first edge of island
//     private final Logger logger = LogManager.getLogger();
//     private String finalEdgeLabel;
//     private Stage nextStage;


//     public EdgeFlight(String finalEdgeLabel, Stage nextStage) {
//         this.finalEdgeLabel = finalEdgeLabel;
//         this.nextStage = nextStage;
//     }

//     public String makeDecision(DirectionManager directionManager, CommandCenter commandCenter, StageManager stageManager) {
//             logger.info("** Flying on {} edge **", finalEdgeLabel);

//             //====== LOGIC FOR CHECKING IF DRONES AT AN ISLAND EDGE ======//
//             // If drone is at the edge of the island, make a U-turn
//             if (poiManager.atIslandEdge(direction.getDirection().stringForward(), cm.getCoordinates(), direction.getDirection().getFullDirectionString(direction.getDirection().stringForward()))) {

//                     sm.setStage(new UTurn(pState.getPrevUTurn(), new InPositionTurn(pState.getPrevUTurn(), nextStage)));
//                     pState.setPrevAction("fly");
//                     return FlightCommands.getInstance().fly();
//             }

//             //====== LOGIC FOR FLYING OVER WATER ======//
//             if (pState.getPrevAction().equals("scan") && overWater(pState)) {

//                 logger.info("** Echoing over water **");
//                 sm.setStage(new FlyDistance(new EdgeFlight(finalEdgeLabel, nextStage)));

//                 pState.setPrevAction("echo");
//                 pState.setPrevHeading(direction.getDirection().stringForward());
//                 return FlightCommands.getInstance().echo(direction.getDirection().stringForward());
//             } 

//             //====== LOGIC FOR FLYING FORWARD AND SCANNING ======//
//             if (pState.getPrevAction().equals("fly")) {
//                 pState.setPrevAction("scan");
//                 return FlightCommands.getInstance().scan();
//             } else {
//                 pState.setPrevAction("fly");
//                 return FlightCommands.getInstance().fly();
//             }
//     }

//     private boolean overWater(PreviousState previousState) {
//         JSONArray biomes = previousState.getBiomes();
//         if (biomes == null) {
//             return false;
//         }

//         boolean waterPresent = false;
//         boolean landPresent = false;
//         for (int i = 0; i < biomes.length(); i++) {
//             if (biomes.getString(i).equals("OCEAN") || biomes.getString(i).equals("LAKE")) {
//                 waterPresent = true;
//             }

//             if (!biomes.getString(i).equals("OCEAN") && !biomes.getString(i).equals("LAKE")) {
//                 landPresent = true;
//             }
//         }

//         if (waterPresent && !landPresent) {
//             return true;
//         }

//         return false;
//     }
// }

