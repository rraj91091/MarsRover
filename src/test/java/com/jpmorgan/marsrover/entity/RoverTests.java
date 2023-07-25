package com.jpmorgan.marsrover.entity;

import com.jpmorgan.marsrover.enums.Direction;
import com.jpmorgan.marsrover.enums.MoveCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoverTests {

    @Nested
    @DisplayName("Testing Move Forward Command")
    class MoveForwardCommandTests {
        @Test
        public void givenRoverFacingEast_RoverShouldMoveEastByOneCoordinate() {
            Rover rover = new Rover("R1",2,2, Direction.EAST);
            rover.move(MoveCommand.FORWARD);
            assertEquals(3, rover.getX());
            assertEquals(2, rover.getY());
            assertEquals(Direction.EAST, rover.getDirection());
        }

        @Test
        public void givenRoverFacingNorth_RoverShouldMoveNorthByOneCoordinate() {
            Rover rover = new Rover("R1",2,2, Direction.NORTH);
            rover.move(MoveCommand.FORWARD);
            assertEquals(2, rover.getX());
            assertEquals(3, rover.getY());
            assertEquals(Direction.NORTH, rover.getDirection());
        }

        @Test
        public void givenRoverFacingSouth_RoverShouldMoveSouthByOneCoordinate() {
            Rover rover = new Rover("R1",2,2, Direction.SOUTH);
            rover.move(MoveCommand.FORWARD);
            assertEquals(2, rover.getX());
            assertEquals(1, rover.getY());
            assertEquals(Direction.SOUTH, rover.getDirection());
        }

        @Test
        public void givenRoverFacingWest_RoverShouldMoveWestByOneCoordinate() {
            Rover rover = new Rover("R1",2,2, Direction.WEST);
            rover.move(MoveCommand.FORWARD);
            assertEquals(1, rover.getX());
            assertEquals(2, rover.getY());
            assertEquals(Direction.WEST, rover.getDirection());
        }
    }

    @Nested
    @DisplayName("Testing Move Backward Command")
    class MoveBackwardCommandTests {
        @Test
        public void givenRoverFacingEast_RoverShouldMoveWestByOneCoordinate() {
            Rover rover = new Rover("R1",2,2, Direction.EAST);
            rover.move(MoveCommand.BACKWARD);
            assertEquals(1, rover.getX());
            assertEquals(2, rover.getY());
            assertEquals(Direction.EAST, rover.getDirection());
        }

        @Test
        public void givenRoverFacingNorth_RoverShouldMoveSouthByOneCoordinate() {
            Rover rover = new Rover("R1",2,2, Direction.NORTH);
            rover.move(MoveCommand.BACKWARD);
            assertEquals(2, rover.getX());
            assertEquals(1, rover.getY());
            assertEquals(Direction.NORTH, rover.getDirection());
        }

        @Test
        public void givenRoverFacingSouth_RoverShouldMoveNorthByOneCoordinate() {
            Rover rover = new Rover("R1",2,2, Direction.SOUTH);
            rover.move(MoveCommand.BACKWARD);
            assertEquals(2, rover.getX());
            assertEquals(3, rover.getY());
            assertEquals(Direction.SOUTH, rover.getDirection());
        }

        @Test
        public void givenRoverFacingWest_RoverShouldMoveEastByOneCoordinate() {
            Rover rover = new Rover("R1",2,2, Direction.WEST);
            rover.move(MoveCommand.BACKWARD);
            assertEquals(3, rover.getX());
            assertEquals(2, rover.getY());
            assertEquals(Direction.WEST, rover.getDirection());
        }
    }

    @Nested
    @DisplayName("Testing Turn Right Command")
    class TurnRightCommandTests {
        @Test
        public void givenRoverFacingEast_RoverShouldTurnToFaceSouth() {
            Rover rover = new Rover("R1",2,2, Direction.EAST);
            rover.move(MoveCommand.TURN_RIGHT);
            assertEquals(2, rover.getX());
            assertEquals(2, rover.getY());
            assertEquals(Direction.SOUTH, rover.getDirection());
        }

        @Test
        public void givenRoverFacingNorth_RoverShouldTurnToFaceEast() {
            Rover rover = new Rover("R1",2,2, Direction.NORTH);
            rover.move(MoveCommand.TURN_RIGHT);
            assertEquals(2, rover.getX());
            assertEquals(2, rover.getY());
            assertEquals(Direction.EAST, rover.getDirection());
        }

        @Test
        public void givenRoverFacingSouth_RoverShouldTurnToFaceWest() {
            Rover rover = new Rover("R1",2,2, Direction.SOUTH);
            rover.move(MoveCommand.TURN_RIGHT);
            assertEquals(2, rover.getX());
            assertEquals(2, rover.getY());
            assertEquals(Direction.WEST, rover.getDirection());
        }

        @Test
        public void givenRoverFacingWest_RoverShouldTurnToFaceNorth() {
            Rover rover = new Rover("R1",2,2, Direction.WEST);
            rover.move(MoveCommand.TURN_RIGHT);
            assertEquals(2, rover.getX());
            assertEquals(2, rover.getY());
            assertEquals(Direction.NORTH, rover.getDirection());
        }
    }

    @Nested
    @DisplayName("Testing Turn Left Command")
    class TurnLeftCommandTests {
        @Test
        public void givenRoverFacingEast_RoverShouldTurnToFaceNorth() {
            Rover rover = new Rover("R1",2,2, Direction.EAST);
            rover.move(MoveCommand.TURN_LEFT);
            assertEquals(2, rover.getX());
            assertEquals(2, rover.getY());
            assertEquals(Direction.NORTH, rover.getDirection());
        }

        @Test
        public void givenRoverFacingNorth_RoverShouldTurnToFaceWest() {
            Rover rover = new Rover("R1",2,2, Direction.NORTH);
            rover.move(MoveCommand.TURN_LEFT);
            assertEquals(2, rover.getX());
            assertEquals(2, rover.getY());
            assertEquals(Direction.WEST, rover.getDirection());
        }

        @Test
        public void givenRoverFacingSouth_RoverShouldTurnToFaceEast() {
            Rover rover = new Rover("R1",2,2, Direction.SOUTH);
            rover.move(MoveCommand.TURN_LEFT);
            assertEquals(2, rover.getX());
            assertEquals(2, rover.getY());
            assertEquals(Direction.EAST, rover.getDirection());
        }

        @Test
        public void givenRoverFacingWest_RoverShouldTurnToFaceSouth() {
            Rover rover = new Rover("R1",2,2, Direction.WEST);
            rover.move(MoveCommand.TURN_LEFT);
            assertEquals(2, rover.getX());
            assertEquals(2, rover.getY());
            assertEquals(Direction.SOUTH, rover.getDirection());
        }
    }


}
