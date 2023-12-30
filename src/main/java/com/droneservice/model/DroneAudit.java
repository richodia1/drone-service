package com.droneservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name ="event-audit")
public @Data class DroneAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToMany
    private List<DroneBatteryLog> drones;
    private LocalDateTime dateTime;


}
