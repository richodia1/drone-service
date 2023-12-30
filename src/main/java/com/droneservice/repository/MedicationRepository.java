package com.droneservice.repository;

import com.droneservice.model.Medication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicationRepository extends CrudRepository<Medication,Long> {

    List<Medication> findAll();
    List<Medication> findAllByIdIn(List<Long> ids);

}
