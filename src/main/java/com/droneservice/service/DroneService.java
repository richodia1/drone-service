package com.droneservice.service;

import com.droneservice.dto.AuditDTO;
import com.droneservice.dto.AvailableDronesDTO;
import com.droneservice.dto.DroneBatteryDTO;
import com.droneservice.dto.DroneDTO;

public interface DroneService {

    public DroneDTO registerDrone(DroneDTO droneDto);
    public DroneDTO getDrone(String serialNumber);
    public AuditDTO getDroneAudit();
    public AvailableDronesDTO listAvailableDrones();
    public DroneBatteryDTO checkBattery(String serialNumber);

}
