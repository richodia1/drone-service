package com.droneservice.service;

import com.droneservice.dto.MedicationDTO;
import com.droneservice.model.Medication;

import java.util.List;

public interface MedicationService {

    public void createMedication(MedicationDTO medDto);
    public List<Medication> createMedication(List<MedicationDTO> medDtos);
    public List<Medication> listMedications();
    public List<Medication> listMedications(List<Long> ids);

}
