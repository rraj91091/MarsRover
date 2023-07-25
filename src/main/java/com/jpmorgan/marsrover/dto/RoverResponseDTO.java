package com.jpmorgan.marsrover.dto;

public class RoverResponseDTO {

    private String rover;
    private PositionDTO position;

    public RoverResponseDTO(String rover, PositionDTO position) {
        this.rover = rover;
        this.position = position;
    }

    public String getRover() {
        return rover;
    }

    public void setRover(String rover) {
        this.rover = rover;
    }

    public PositionDTO getPosition() {
        return position;
    }

    public void setPosition(PositionDTO position) {
        this.position = position;
    }

}
