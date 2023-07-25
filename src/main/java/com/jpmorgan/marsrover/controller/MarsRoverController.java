package com.jpmorgan.marsrover.controller;

import com.jpmorgan.marsrover.service.CommandParserService;
import com.jpmorgan.marsrover.entity.Rover;
import com.jpmorgan.marsrover.service.RoverService;
import com.jpmorgan.marsrover.dto.RoverResponseDTO;
import com.jpmorgan.marsrover.enums.Direction;
import com.jpmorgan.marsrover.enums.MoveCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/mars-rover")
public class MarsRoverController {

    private final RoverService roverService;
    private final CommandParserService commandParserService;

    @Autowired
    public MarsRoverController(RoverService roverService, CommandParserService commandParserService) {
        this.roverService = roverService;
        this.commandParserService = commandParserService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<RoverResponseDTO> createRover(@RequestParam("coordinates") String coordinates) {
        int xCoord = commandParserService.getXCoordinate(coordinates);
        int yCoord = commandParserService.getYCoordinate(coordinates);
        Direction direction = commandParserService.getDirection(coordinates);
        Rover newRover = roverService.createRover(xCoord, yCoord, direction);
        return ResponseEntity.status(HttpStatus.CREATED).body(newRover.toResponseDTO());
    }

    @GetMapping(value = "/position")
    public ResponseEntity<RoverResponseDTO> getCurrentPosition(@RequestParam("rover") String roverName) {
        Rover existingRover = roverService.getRover(roverName);
        return ResponseEntity.status(HttpStatus.OK).body(existingRover.toResponseDTO());
    }

    @PutMapping(value = "/move")
    public ResponseEntity<RoverResponseDTO> moveRover(
            @RequestParam("rover") String roverName,
            @RequestParam("commands") String commands
    ) {
        List<MoveCommand> moveCommands = commandParserService.parseCommands(commands);
        Rover rover = roverService.moveRover(roverName, moveCommands);
        return ResponseEntity.status(HttpStatus.OK).body(rover.toResponseDTO());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }

}