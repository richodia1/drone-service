package com.droneservice.service;

import com.droneservice.controller.Response;
import com.droneservice.dto.DroneActivityDTO;
import com.droneservice.dto.LoadDroneDTO;

public interface DroneActivityService {

    public Response loadDrone(LoadDroneDTO drontActivityDto);
    public DroneActivityDTO getDroneActivity(String serialNumber);

}
