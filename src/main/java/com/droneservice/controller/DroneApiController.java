package com.droneservice.controller;

import com.droneservice.dto.AuditDTO;
import com.droneservice.dto.AvailableDronesDTO;
import com.droneservice.dto.DroneBatteryDTO;
import com.droneservice.dto.DroneDTO;
import com.droneservice.service.DroneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping(path = {"/api/v1/drones"}, produces = APPLICATION_JSON_VALUE)
public class DroneApiController {

    @Autowired
    private DroneService droneService;

    @PostMapping(value="/register",consumes=APPLICATION_JSON_VALUE)
    @Operation(summary = "Register a new drone")
    @ApiResponse(responseCode = "201", description = "New drone created",
            content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = DroneDTO.class))})
    public ResponseEntity<DroneDTO> registerDrone(@Valid @RequestBody DroneDTO dto){

        DroneDTO _dto = droneService.registerDrone(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(_dto);
    }



    @Operation(summary = "Get a drone with its battery life using serial number")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Found the drone",
            content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = DroneBatteryDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Drone not found", content = @Content)})
    @GetMapping(path = "/{serialNumber}")
    public ResponseEntity<DroneBatteryDTO> getDroneBattery(@PathVariable("serialNumber")String serialNumber){

        DroneBatteryDTO dto = droneService.checkBattery(serialNumber);
        if(dto!=null) {
            return ResponseEntity.ok(dto);
        }
        else
            return ResponseEntity.notFound().build();

    }


    @GetMapping("/available")
    @Operation(summary = "Get a list of drones available for loading")
    @ApiResponse(responseCode = "201", description = "Drones found",
            content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = AvailableDronesDTO.class))})
    public ResponseEntity<AvailableDronesDTO> findAvailableDrones(){

        AvailableDronesDTO dto = droneService.listAvailableDrones();
        return ResponseEntity.ok(dto);
    }



    @GetMapping("/audit")
    public ResponseEntity<AuditDTO> getDroneAudit(){
        AuditDTO audit = droneService.getDroneAudit();
        return ResponseEntity.ok(audit);

    }
    @GetMapping("/beauty")
    public ResponseEntity<AuditDTO> getBeautiful(){
        AuditDTO audit = droneService.getDroneAudit();
        return ResponseEntity.ok(audit);

    }


}
