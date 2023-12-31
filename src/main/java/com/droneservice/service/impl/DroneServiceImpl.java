package com.droneservice.service.impl;

import com.droneservice.dto.AuditDTO;
import com.droneservice.dto.AvailableDronesDTO;
import com.droneservice.dto.DroneBatteryDTO;
import com.droneservice.dto.DroneDTO;
import com.droneservice.model.Drone;
import com.droneservice.model.DroneState;
import com.droneservice.repository.DroneRepository;
import com.droneservice.service.DroneService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DroneServiceImpl implements DroneService {


    private DroneRepository droneRepo;


    private final ModelMapper modelMapper;

    private int BATTERY_LIMIT=25;

    @Autowired
    public DroneServiceImpl(DroneRepository droneRepo,ModelMapper modelMapper) {
        this.droneRepo = droneRepo;
        this.modelMapper = modelMapper;
    }




    @Override
    public DroneDTO registerDrone(DroneDTO droneDto) {

        Drone drone = modelMapper.map(droneDto,  Drone.class);
        if(drone.getBatteryLife()>BATTERY_LIMIT) {
            drone.setDroneState(DroneState.LOADING);
        }
        else {
            drone.setDroneState(DroneState.IDLE);
        }


        drone.setCurrentWeight(0.0);
        Drone _drone = droneRepo.save(drone);

        return   modelMapper.map(_drone,DroneDTO.class);

    }

    @Override
    public DroneDTO getDrone(String serialNumber) {

        Drone drone = droneRepo.findBySerialNumber(serialNumber);

        return  modelMapper.map(drone,DroneDTO.class);
    }

    @Override
    public AuditDTO getDroneAudit() {

        List<Drone> drones = droneRepo.findAll();


        List<DroneBatteryDTO> dtos = drones.stream()
                .map(entity -> modelMapper.map(entity, DroneBatteryDTO.class))
                .collect(Collectors.toList());
        
        AuditDTO audit = new AuditDTO();
        audit.setDateTime(LocalDateTime.now());
        audit.setDrones(dtos);
        audit.setTotal(dtos.size());
        return audit;
    }

    @Override
    public AvailableDronesDTO listAvailableDrones() {

        List<Drone> drones = droneRepo.findByDroneState(DroneState.LOADING);
        System.out.println("SIze is : "+drones.size());
        List<DroneDTO> droneDTOs = drones.stream()
                .map(drone -> modelMapper.map(drone, DroneDTO.class))
                .collect(Collectors.toList());
        System.out.println("Size after mapping  is : "+droneDTOs.size());

        AvailableDronesDTO availableDronesDTO = new AvailableDronesDTO();
        availableDronesDTO.setTotal(drones.size());
        availableDronesDTO.setDrones(droneDTOs);

        return availableDronesDTO;
    }

    @Override
    public DroneBatteryDTO checkBattery(String serialNumber) {
        // TODO Auto-generated method stub
        Drone drone = droneRepo.findBySerialNumber(serialNumber);
        return modelMapper.map(drone,DroneBatteryDTO.class);
    }

}
