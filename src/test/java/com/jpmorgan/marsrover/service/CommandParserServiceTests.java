package com.jpmorgan.marsrover.service;

import com.jpmorgan.marsrover.enums.Direction;
import com.jpmorgan.marsrover.enums.MoveCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommandParserServiceTests {

    private final CommandParserService commandParserService = new CommandParserService();

    @Nested
    @DisplayName("getXCoordinate Tests")
    class TestGetXCoordinate {

        @Test
        public void whenValidCommandIsGiven_getXCoordinate_shouldExtractIntegerValueWithoutErrors() {
            int x = commandParserService.getXCoordinate("1,2,N");
            assertEquals(1, x);
        }

        @Test
        public void whenInvalidCommandIsGiven_getXCoordinate_shouldThrowIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> {
                commandParserService.getXCoordinate("a,2,N");
            });
        }
    }

    @Nested
    @DisplayName("getYCoordinate Tests")
    class TestGetYCoordinate {

        @Test
        public void whenValidCommandIsGiven_getYCoordinate_shouldExtractIntegerValueWithoutErrors() {
            int y = commandParserService.getYCoordinate("1,2,N");
            assertEquals(2, y);
        }

        @Test
        public void whenInvalidCommandIsGiven_getYCoordinate_shouldThrowIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> {
                commandParserService.getYCoordinate("1,a,N");
            });
        }
    }

    @Nested
    @DisplayName("GetDirection Tests")
    class TestGetDirection {

        @Test
        public void whenNorthDirectionIsGiven_GetDirection_shouldReturnCorrectDirectionEnumWithoutErrors() {
            Direction direction = commandParserService.getDirection("1,2,N");
            assertEquals(Direction.NORTH, direction);
        }

        @Test
        public void whenSouthDirectionIsGiven_GetDirection_shouldReturnCorrectDirectionEnumWithoutErrors() {
            Direction direction = commandParserService.getDirection("1,2,S");
            assertEquals(Direction.SOUTH, direction);
        }

        @Test
        public void whenEastDirectionIsGiven_GetDirection_shouldReturnCorrectDirectionEnumWithoutErrors() {
            Direction direction = commandParserService.getDirection("1,2,E");
            assertEquals(Direction.EAST, direction);
        }

        @Test
        public void whenWestDirectionIsGiven_GetDirection_shouldReturnCorrectDirectionEnumWithoutErrors() {
            Direction direction = commandParserService.getDirection("1,2,W");
            assertEquals(Direction.WEST, direction);
        }

        @Test
        public void whenInvalidDirectionValueIsGiven_GetDirection_shouldThrowIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> {
                commandParserService.getDirection("1,2,a");
            });
        }
    }

    @Nested
    @DisplayName("ParseCommands Tests")
    class TestParseCommands {

        @Test
        public void whenValidCommandsAreGiven_parseCommands_shouldParseAllCommandsCorrectlyInTheCorrectOrder() {
            List<MoveCommand> commands = commandParserService.parseCommands("f,b,r,l");
            assertEquals(4, commands.size());
            assertEquals(MoveCommand.FORWARD, commands.get(0));
            assertEquals(MoveCommand.BACKWARD, commands.get(1));
            assertEquals(MoveCommand.TURN_RIGHT, commands.get(2));
            assertEquals(MoveCommand.TURN_LEFT, commands.get(3));
        }

        @Test
        public void whenAnInvalidCommandIsGiven_parseCommands_shouldThrowIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> {
                commandParserService.parseCommands("f,b,a,r,l");
            });
        }
    }
}
