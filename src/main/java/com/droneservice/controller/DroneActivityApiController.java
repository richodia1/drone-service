package com.droneservice.controller;

import com.droneservice.dto.DroneActivityDTO;
import com.droneservice.dto.LoadDroneDTO;
import com.droneservice.service.DroneActivityService;
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

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = {"/api/v1/drones/activity"}, produces = APPLICATION_JSON_VALUE)
public class DroneActivityApiController {
    @Autowired
    private DroneActivityService activityService;


    @PostMapping(value="/load",consumes=APPLICATION_JSON_VALUE)
    @Operation(summary = "Load drone with medication")
    @ApiResponse(responseCode = "201", description = "Drone loaded",
            content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = Response.class))})
    public ResponseEntity<Response> loadDrone(@Valid @RequestBody LoadDroneDTO dto){
        Response res = activityService.loadDrone(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }



    @GetMapping("/check/{serialNumber}")
    @Operation(summary = "Get drone activity using serial number")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Found the drone activity",
            content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = LoadDroneDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Drone not found", content = @Content)})
    public ResponseEntity<DroneActivityDTO> findAvailableDroneActivity(@PathVariable("serialNumber")String ref){

        DroneActivityDTO dto = activityService.getDroneActivity(ref);
        if(dto!=null)
            return ResponseEntity.ok(dto);
        else
            return ResponseEntity.notFound().build();
    }




}
