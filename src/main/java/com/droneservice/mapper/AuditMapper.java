package com.droneservice.mapper;
import com.droneservice.dto.AuditDTO;
import com.droneservice.dto.DroneBatteryDTO;
import com.droneservice.model.DroneAudit;
import com.droneservice.model.DroneBatteryLog;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel="spring")
public interface AuditMapper {

    AuditMapper INSTANCE = Mappers.getMapper(AuditMapper.class);

    @Mapping(target="model", source="log.droneModel")
    DroneBatteryDTO batteryToDTO(DroneBatteryLog log);

    List<DroneBatteryDTO> modelsToDto(List<DroneBatteryLog> logs);

    @InheritInverseConfiguration
    DroneBatteryLog dtoToBatteryModel(DroneBatteryDTO dto);


    @InheritInverseConfiguration
    DroneAudit dtoToAudit(AuditDTO auditDTO);

    AuditDTO auditToDTO(DroneAudit audit);

}
