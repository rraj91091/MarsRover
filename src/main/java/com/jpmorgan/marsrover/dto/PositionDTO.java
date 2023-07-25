package com.jpmorgan.marsrover.dto;

public class PositionDTO {

    private String coordinate;
    private String direction;

    public PositionDTO(String coordinate, String direction) {
        this.coordinate = coordinate;
        this.direction = direction;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}