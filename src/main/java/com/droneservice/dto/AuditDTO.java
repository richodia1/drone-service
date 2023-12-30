package com.droneservice.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

public @Data class AuditDTO {

    private List<DroneBatteryDTO> drones;
    private int total;
    private LocalDateTime dateTime;

}
