package com.jpmorgan.marsrover.service;

import com.jpmorgan.marsrover.entity.Rover;
import com.jpmorgan.marsrover.enums.Direction;
import com.jpmorgan.marsrover.enums.MoveCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RoverServiceTests {

    private final RoverService roverService = new RoverService();

    @Nested
    @DisplayName("Rover Creation Tests")
    class RoverCreationTests {

        @Test
        public void givenNoExistingRovers_CreateRoverShouldCreateNewRoverAtSpecifiedPoint() {
            Rover newRover = roverService.createRover(2, 3, Direction.EAST);

            assertEquals("R1", newRover.getName());
            assertEquals(2, newRover.getX());
            assertEquals(3, newRover.getY());
            assertEquals(Direction.EAST, newRover.getDirection());
        }

        @Test
        public void givenExistingRoverAtDifferentPoint_CreateRoverShouldCreateNewRoverAtSpecifiedPoint() {
            roverService.createRover(3, 4, Direction.EAST);

            Rover newRover = roverService.createRover(2, 3, Direction.EAST);

            assertEquals("R2", newRover.getName());
            assertEquals(2, newRover.getX());
            assertEquals(3, newRover.getY());
            assertEquals(Direction.EAST, newRover.getDirection());
        }

        @Test
        public void givenExistingRoverAtTheSamePoint_CreateRoverShouldThrowIllegalArgumentException() {
            roverService.createRover(2, 3, Direction.NORTH);

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                roverService.createRover(2, 3, Direction.EAST);
            });
            assertEquals("A rover already exists at the specified coordinates. " +
                    "Unable to create new rover.", exception.getMessage());
        }
    }

    @Nested
    @DisplayName("Get Rover Tests")
    class GetRoverTests {

        @Test
        public void givenExistingRover_GetRoverShouldReturnRoverDetails() {
            roverService.createRover(2, 3, Direction.NORTH);

            Rover rover = roverService.getRover("R1");

            assertEquals("R1", rover.getName());
            assertEquals(2, rover.getX());
            assertEquals(3, rover.getY());
            assertEquals(Direction.NORTH, rover.getDirection());
        }

        @Test
        public void givenNoExistingRover_GetRoverShouldThrowIllegalArgumentException() {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                roverService.getRover("R1");
            });
            assertEquals("Invalid Rover Name: R1", exception.getMessage());
        }
    }

    @Nested
    @DisplayName("Move Rover Tests")
    class MoveRoverTests {
        @Test
        public void givenNoOtherRoversInPath_SelectedRoverShouldMoveToTargetLocation() {
            List<MoveCommand> commands = Arrays.asList(MoveCommand.FORWARD, MoveCommand.FORWARD);
            Rover rover = roverService.createRover(2, 3, Direction.EAST);

            roverService.moveRover(rover.getName(), commands);

            Rover newRoverPosition = roverService.getRover(rover.getName());
            assertEquals(4, newRoverPosition.getX());
            assertEquals(3, newRoverPosition.getY());
            assertEquals(Direction.EAST, newRoverPosition.getDirection());
        }

        @Test
        public void givenAnotherRoverInPath_SelectedRoverShouldStopJustBeforeTheCollisionPoint() {
            List<MoveCommand> commands = Arrays.asList(MoveCommand.FORWARD, MoveCommand.FORWARD, MoveCommand.FORWARD);
            Rover firstRover = roverService.createRover(5, 3, Direction.NORTH);
            Rover secondRover = roverService.createRover(2, 3, Direction.EAST);

            roverService.moveRover(secondRover.getName(), commands);

            Rover newRoverPosition = roverService.getRover(secondRover.getName());
            assertEquals(4, newRoverPosition.getX());
            assertEquals(3, newRoverPosition.getY());
            assertEquals(Direction.EAST, newRoverPosition.getDirection());
        }
    }
}
