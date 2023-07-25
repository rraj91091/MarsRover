package com.jpmorgan.marsrover.entity;

import com.jpmorgan.marsrover.dto.PositionDTO;
import com.jpmorgan.marsrover.dto.RoverResponseDTO;
import com.jpmorgan.marsrover.enums.Direction;
import com.jpmorgan.marsrover.enums.MoveCommand;

import static com.jpmorgan.marsrover.enums.Direction.*;

public class Rover {

    private final String name;
    private int x;
    private int y;
    private Direction direction;

    public Rover(String name, int x, int y, Direction direction) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }

    public Rover move(MoveCommand command) {
        switch(command) {
            case FORWARD: {
                moveForward();
                break;
            }
            case BACKWARD: {
                moveBackward();
                break;
            }
            case TURN_RIGHT: {
                turnRight();
                break;
            }
            case TURN_LEFT: {
                turnLeft();
                break;
            }
        }
        return this;
    }

    private void moveForward() {
        switch(direction) {
            case NORTH: {
                y++;
                break;
            }
            case EAST: {
                x++;
                break;
            }
            case SOUTH: {
                y--;
                break;
            }
            case WEST: {
                x--;
                break;
            }
        }
    }

    private void moveBackward() {
        switch(direction) {
            case NORTH: {
                y--;
                break;
            }
            case EAST: {
                x--;
                break;
            }
            case SOUTH: {
                y++;
                break;
            }
            case WEST: {
                x++;
                break;
            }
        }
    }

    private void turnRight() {
        switch(direction) {
            case NORTH: {
                direction = EAST;
                break;
            }
            case EAST: {
                direction = SOUTH;
                break;
            }
            case SOUTH: {
                direction = WEST;
                break;
            }
            case WEST: {
                direction = NORTH;
                break;
            }
        }
    }

    private void turnLeft() {
        switch(direction) {
            case NORTH: {
                direction = WEST;
                break;
            }
            case EAST: {
                direction = NORTH;
                break;
            }
            case SOUTH: {
                direction = EAST;
                break;
            }
            case WEST: {
                direction = SOUTH;
                break;
            }
        }
    }

    @Override
    public String toString() {
        return "Rover{" +
                "name='" + name + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", direction=" + direction +
                '}';
    }

    public Rover copy() {
        return new Rover(this.name, this.x, this.y, this.direction);
    }

    public RoverResponseDTO toResponseDTO() {
        return new RoverResponseDTO(
                name,
                new PositionDTO(x + "," + y, direction.name())
        );
    }
}
