package com.jpmorgan.marsrover.enums;

public enum MoveCommand {
    FORWARD("f"),
    BACKWARD("b"),
    TURN_RIGHT("r"),
    TURN_LEFT("l");

    private final String shortName;

    MoveCommand(String shortName) {
        this.shortName = shortName;
    }

    public static MoveCommand fromString(String shortName) {
        for (MoveCommand command : MoveCommand.values()) {
            if (command.shortName.equals(shortName)) {
                return command;
            }
        }

        throw new IllegalArgumentException("Invalid Command: " + shortName);
    }
}
