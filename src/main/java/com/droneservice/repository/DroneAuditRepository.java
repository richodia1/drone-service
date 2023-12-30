package com.droneservice.repository;

import com.droneservice.model.DroneAudit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneAuditRepository extends CrudRepository<DroneAudit,Long> {

    List<DroneAudit> findAll();
}
