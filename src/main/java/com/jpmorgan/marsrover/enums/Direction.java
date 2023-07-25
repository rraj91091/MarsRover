package com.jpmorgan.marsrover.enums;

public enum Direction {
    NORTH("N"),
    SOUTH("S"),
    EAST("E"),
    WEST("W");

    private final String shortName;

    Direction(String shortName) {
        this.shortName = shortName;
    }

    public static Direction fromString(String shortName) {
        for (Direction direction : Direction.values()) {
            if (direction.shortName.equals(shortName)) {
                return direction;
            }
        }

        throw new IllegalArgumentException("Invalid Direction: " + shortName);
    }
}
