package com.droneservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Table(name ="drones")
public @Data class Drone {

    @Id
    @Column(name="serial_number", length = 100)
    private String serialNumber;

    @Column(name="model")
    @Enumerated(value = EnumType.STRING)
    private DroneModel droneModel;

    @Column(name="weight_max")
    @DecimalMax(value = "500", message =" Drone cannot carry more than {value} grams")
    private Double maxWeight; // Maximum weight the drone can carry

    @Column(name="battery_capacity")
    @Max(value=100,message="Battery life cannot be more than 100%")
    private Integer batteryLife; // Battery capacity measured in percentage %

    @Column(name = "drone_state")
    @Enumerated(value = EnumType.STRING)
    private DroneState droneState; // Current state of the drone

    @Column(name = "current_weight")
    private Double currentWeight; // This will keep track of the weight of the drone during loading, to prevent

}
