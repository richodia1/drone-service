package com.droneservice.dto;

import lombok.Data;

import java.util.List;

public @Data class DroneActivityDTO {

    private int total;
    private List<LoadDroneDTO> activities;
}
