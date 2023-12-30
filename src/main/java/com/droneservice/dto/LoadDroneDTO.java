package com.droneservice.dto;


import com.fasterxml.jackson.annotation.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

public @Data class LoadDroneDTO {

    @NotNull(message = "Drone serial umber must not be empty")
    @Size(min=3,max=100,message="Drone serial number must not be greater than {value} characters")
    @JsonProperty(required = true)
    @NotEmpty
    @NotBlank
    private String serialNumber;

    private String droneModel;

    @JsonProperty(required = false)
    private String droneState;

    @JsonProperty(required = true)
    @NotNull(message="Quantity must not be empty")
    private Integer quantity;

    @JsonProperty(required = true)
    @NotNull(message="Total weight must not be empty")
    private Double totalWeight;

    @JsonProperty(required = true)
    private String deliveryAddress;

    @JsonProperty(required = true)
    @NotEmpty(message="Add medications before loading to drone")
    private List<MedicationDTO> medications;
}

