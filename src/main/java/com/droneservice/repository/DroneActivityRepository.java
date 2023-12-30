package com.droneservice.repository;

import com.droneservice.model.DroneActivity;
import com.droneservice.model.DroneState;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneActivityRepository extends CrudRepository<DroneActivity,Long> {

    List<DroneActivity> findByDroneIdAndStateIn(String droneId, List<DroneState> states);
}
