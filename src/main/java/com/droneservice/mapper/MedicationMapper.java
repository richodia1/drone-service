package com.droneservice.mapper;

import com.droneservice.dto.MedicationDTO;
import com.droneservice.model.Medication;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(componentModel="spring")
public interface MedicationMapper {

    MedicationMapper INSTANCE = Mappers.getMapper(MedicationMapper.class);

    MedicationDTO medicationToDTOo(Medication medication);

    @InheritInverseConfiguration
    Medication dtoToMedication(MedicationDTO dto);

    @InheritInverseConfiguration
    List<Medication> dtosToMedications(List<MedicationDTO> dtos);

}
