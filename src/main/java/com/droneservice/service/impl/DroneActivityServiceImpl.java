package com.droneservice.service.impl;

import com.droneservice.controller.Response;
import com.droneservice.dto.DroneActivityDTO;
import com.droneservice.dto.LoadDroneDTO;
import com.droneservice.model.Drone;
import com.droneservice.model.DroneActivity;
import com.droneservice.model.DroneState;
import com.droneservice.model.Medication;
import com.droneservice.repository.DroneActivityRepository;
import com.droneservice.repository.DroneRepository;
import com.droneservice.service.DroneActivityService;
import com.droneservice.service.MedicationService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DroneActivityServiceImpl implements DroneActivityService {


    @Autowired
    private DroneActivityRepository activityRepo;


    @Autowired private DroneRepository droneRepo;
    private final ModelMapper modelMapper;
    @Autowired private MedicationService medService;

    @Autowired public DroneActivityServiceImpl(ModelMapper modelMapper) {
       this.modelMapper = modelMapper;
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setAmbiguityIgnored(true)  // Ignore ambiguous matches
                .setSkipNullEnabled(true);
    }


    /**
     * This function will perform the following actions
     * 1. Find the drone with the serial number
     * 2. Check if the drone is in loading state
     * 3. Check if the total weight loaded is heavier than the drone capacity. Though this check is performed using bean validation api,
     * but just to perform additional check;
     * 4. Check if the weight loaded is heavier than the available space. Might happen when the drone is partially loaded
     * 5. Load the drone and update drone status if necessary
     *
     * @param drontActivityDto DroneActivityDTO object
     */

    @Transactional
    @Override
    public Response loadDrone(LoadDroneDTO drontActivityDto) {
        //Get the drone
        Drone drone = droneRepo.findBySerialNumber(drontActivityDto.getSerialNumber());

        if(drone == null)
            return parseResponse("Drone does not exist. Check the serial number","error");

        List<Medication> meds = medService.createMedication(drontActivityDto.getMedications());


        DroneActivity activity = modelMapper.map(drone, DroneActivity.class);

        //Perform checks
        if(drone.getDroneState() != DroneState.LOADING)
            return parseResponse("Drone is fully loaded and no longer available, please try another drone","error");

        if(activity.getWeightLoaded()>drone.getMaxWeight())
            return parseResponse("The weight loaded is heavier than the drone capacity","error");

        double availableWeight = drone.getMaxWeight() - drone.getCurrentWeight();

        if(activity.getWeightLoaded()>availableWeight)
            return parseResponse("Weight loaded is greater than available space","error");

        double totalWeight = drone.getCurrentWeight()+activity.getWeightLoaded();

        //Load drone and update status

        drone.setCurrentWeight(totalWeight);
        if(drone.getMaxWeight() == totalWeight) {
            drone.setDroneState(DroneState.LOADED);
        }
        activity.setState(drone.getDroneState());
        activity.setMedications(meds);
        activityRepo.save(activity);
        droneRepo.save(drone);

        return parseResponse(drone.getSerialNumber(),"OK");


    }

    private Response parseResponse(String res,String status) {
        Response resp = new Response();
        resp.setData(res);
        resp.setStatus(status);
        return resp;

    }

    /**
     * This function will load the activities of a given drone using it's serial number if the drone is in
     * LOADING OR LOADED state.
     *
     * @param serialnumber
     */

    @Override
    public DroneActivityDTO getDroneActivity(String serialnumber) {


        Drone drone = droneRepo.findBySerialNumber(serialnumber);
        List<DroneState> states = new ArrayList<>();
        states.add(DroneState.LOADING);
        states.add(DroneState.LOADED);


        List<DroneActivity> activities = activityRepo.findByDroneIdAndStateIn(serialnumber, states);
        System.out.println(activities);
        List<LoadDroneDTO> loadedActivities = activities.stream()
                .map(entity -> modelMapper.map(entity, LoadDroneDTO.class))
                .collect(Collectors.toList());

        DroneActivityDTO dto = new DroneActivityDTO();
        dto.setTotal(activities.size());
        dto.setActivities(loadedActivities);

        return dto;
    }

}

