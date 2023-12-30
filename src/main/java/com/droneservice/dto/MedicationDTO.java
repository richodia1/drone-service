package com.droneservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

public @Data class MedicationDTO {

    @JsonProperty(required = false)
    private Long id;

    @JsonProperty(required = true)
    @NotEmpty
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9.\\-\\/_ ]*$", message="Only numbers, letters, underscore and dash characters are allowed")
    private String name;

    @JsonProperty(required = true)
    @Max(value=500, message="Weight too heavy for drone")
    private Double weight;


    @JsonProperty(required = true)
    @NotEmpty
    @NotBlank
    @Pattern(regexp = "^[A-Z0-9.\\-\\/_ ]*$", message="Only numbers, capital letters, underscore and dash characters are allowed")
    private String code;


    private String image;

}
