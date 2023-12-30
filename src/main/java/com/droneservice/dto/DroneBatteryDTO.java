package com.droneservice.dto;

import lombok.Data;

public @Data class DroneBatteryDTO {

    private String serialNumber;
    private String model;
    private int batteryLife;

}
