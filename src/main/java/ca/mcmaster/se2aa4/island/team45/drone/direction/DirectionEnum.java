package ca.mcmaster.se2aa4.island.team45.drone.direction;

public enum DirectionEnum {
    NORTH("N", "North"),
    EAST("E", "East"),
    SOUTH("S", "South"),
    WEST("W", "West");

    private final String shortDir;
    private final String longDir;

    DirectionEnum(String shortDir, String longDir) {
        this.shortDir = shortDir;
        this.longDir = longDir;
    }

    public String getshortDir() {
        return shortDir;
    }

    public String getlongDir() {
        return longDir;
    }

    public static DirectionEnum fromString(String direction) {
        for (DirectionEnum dir : DirectionEnum.values()) {
            if (dir.shortDir.equals(direction) || dir.longDir.equalsIgnoreCase(direction)) {
                return dir;
            }
        }
        throw new IllegalArgumentException("Unknown direction: " + direction);
    }
}