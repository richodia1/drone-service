package com.droneservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name="drone_activity")
public @Data class DroneActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @Column(name="drone_id")
    private String droneId;

    @Column(name="activity_state")
    @Enumerated(value = EnumType.STRING)
    private DroneState state;

    @Column(name="loaded_medications")
    @OneToMany
    private List<Medication> medications;

    @Column(name="quantity_loaded")
    private Integer quantity;

    @Column(name="max_weight_loaded")
    private Double weightLoaded = 0.0;

    @Column(name="destinationm_address",columnDefinition="TEXT")
    private String address;

}
