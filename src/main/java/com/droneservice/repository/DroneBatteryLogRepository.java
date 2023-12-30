package com.droneservice.repository;

import com.droneservice.model.DroneBatteryLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DroneBatteryLogRepository extends CrudRepository<DroneBatteryLog,Long> {
}
