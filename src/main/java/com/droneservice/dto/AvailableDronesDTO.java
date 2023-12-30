package com.droneservice.dto;

import lombok.Data;

import java.util.List;

public @Data class AvailableDronesDTO {

    private int total;
    private List<DroneDTO> drones;

}
