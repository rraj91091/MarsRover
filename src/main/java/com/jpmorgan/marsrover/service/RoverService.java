package com.jpmorgan.marsrover.service;

import com.jpmorgan.marsrover.entity.Rover;
import com.jpmorgan.marsrover.enums.Direction;
import com.jpmorgan.marsrover.enums.MoveCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RoverService {

    private final Logger logger = LoggerFactory.getLogger(RoverService.class);
    private final Map<String, Rover> rovers = new HashMap<>();

    public Rover createRover(int xCoord, int yCoord, Direction direction) {
        String roverName = createNewRoverName();
        Rover newRover = new Rover(roverName, xCoord, yCoord, direction);

        if (willCollide(newRover)) {
            throw new IllegalArgumentException("A rover already exists at the specified coordinates. " +
                    "Unable to create new rover.");
        } else {
            rovers.put(roverName, newRover);
        }

        return newRover;
    }

    public Rover getRover(String roverName) {
        Rover rover = rovers.get(roverName);
        if (rover == null) {
            throw new IllegalArgumentException("Invalid Rover Name: " + roverName);
        }
        return rover;
    }

    public Rover moveRover(String roverName, List<MoveCommand> commands) {
        Rover rover = getRover(roverName);
        for (MoveCommand command : commands) {
            Rover newRoverPosition = rover.copy().move(command);

            if (willCollide(newRoverPosition)) {
                logger.info("Impending collision at coordinates: [{},{}]",
                        newRoverPosition.getX(), newRoverPosition.getY());
                break;
            } else {
                logger.info("Rover Moving to new location: {}", newRoverPosition.toString());
                rover.move(command);
            }
        }

        return rover;
    }

    private String createNewRoverName() {
        return "R" + (rovers.size() + 1);
    }

    private boolean willCollide(Rover newRover) {
        for (Rover rover : rovers.values()) {
            if (rover.getX() == newRover.getX()
                    && rover.getY() == newRover.getY()
                    && !newRover.getName().equals(rover.getName())
            ) {
                return true;
            }
        }
        return false;
    }

}
