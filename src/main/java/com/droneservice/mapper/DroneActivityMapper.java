package com.droneservice.mapper;

import com.droneservice.dto.DroneActivityDTO;
import com.droneservice.dto.LoadDroneDTO;
import com.droneservice.model.Drone;
import com.droneservice.model.DroneActivity;
import com.droneservice.model.Medication;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Stream;

@Mapper(componentModel="spring")
public interface DroneActivityMapper {

    DroneActivityMapper INSTANCE = Mappers.getMapper(DroneActivityMapper.class);


    @Mapping(target="serialNumber", source="activity.droneId")
    @Mapping(target="droneModel",source="drone.droneModel")
    @Mapping(target="droneState",source="drone.droneState")
    @Mapping(target="totalWeight",source="activity.weightLoaded")
    @Mapping(target="deliveryAddress",source="activity.address")
    @Mapping(target="medications",source="meds")
    LoadDroneDTO activityToDTO(DroneActivity activity, Drone drone, Stream<Medication> meds);


    @Mapping(target="droneId",source="activity.serialNumber")
    @Mapping(target="weightLoaded",source="activity.totalWeight")
    @Mapping(target="address",source="activity.deliveryAddress")
        //@Mapping(target="medications",source="meds")
    DroneActivity dtoToActivity(LoadDroneDTO activity,Drone drone);

    @Mapping(target="total",source="total")
    @Mapping(target="activities",source="droneActivities")
    DroneActivityDTO mapActivityDTO(int total, List<LoadDroneDTO> droneActivities);



}

