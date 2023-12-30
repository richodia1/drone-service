package com.droneservice.model;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name ="drone-battery-log")
public @Data class DroneBatteryLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String droneModel;
    private String serialNumber;
    private Integer batteryLife;
}
