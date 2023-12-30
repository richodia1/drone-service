package com.droneservice.repository;

import com.droneservice.model.Drone;
import com.droneservice.model.DroneState;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneRepository extends CrudRepository<Drone,String> {

    Drone findBySerialNumber(String serialNumber);
    List<Drone> findAll();

    List<Drone> findByDroneState(DroneState state);

}
