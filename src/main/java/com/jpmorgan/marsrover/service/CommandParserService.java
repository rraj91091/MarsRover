package com.jpmorgan.marsrover.service;

import com.jpmorgan.marsrover.enums.Direction;
import com.jpmorgan.marsrover.enums.MoveCommand;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommandParserService {

    public int getXCoordinate(String command) {
        return Integer.parseInt(command.split(",")[0]);
    }

    public int getYCoordinate(String command) {
        return Integer.parseInt(command.split(",")[1]);
    }

    public Direction getDirection(String command) {
        return Direction.fromString(command.split(",")[2]);
    }

    public List<MoveCommand> parseCommands(String moveCommands) {
        return Arrays.stream(moveCommands.split(","))
                .map(MoveCommand::fromString)
                .collect(Collectors.toList());
    }
}
