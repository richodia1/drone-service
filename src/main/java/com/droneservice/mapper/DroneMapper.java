package com.droneservice.mapper;

import com.droneservice.dto.AvailableDronesDTO;
import com.droneservice.dto.DroneBatteryDTO;
import com.droneservice.dto.DroneDTO;
import com.droneservice.model.Drone;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel="spring")
public interface DroneMapper {

    DroneMapper INSTANCE = Mappers.getMapper(DroneMapper.class);


    @Mapping(target="weightLoaded",source="drone.currentWeight")
    DroneDTO modelToDto(Drone drone);

    List<DroneDTO> dronesToDto(List<Drone> drones);

    List<DroneBatteryDTO> dronesToBatteryLog(List<Drone> drones);


    @InheritInverseConfiguration
    Drone dtoToDrone(DroneDTO dto);

    @Mapping(target="serialNumber",source="drone.serialNumber")
    @Mapping(target="model",source="drone.droneModel")
    @Mapping(target="batteryLife",source="drone.batteryLife")
    DroneBatteryDTO droneToBetteryDto(Drone drone);

    @Mapping(target="total",source="total")
    @Mapping(target="drones",source="availableDrones")
    AvailableDronesDTO dronesToAvailableDTO(int total, List<Drone> availableDrones);

}
