package com.droneservice.service.impl;

import com.droneservice.dto.MedicationDTO;
import com.droneservice.model.Medication;
import com.droneservice.repository.MedicationRepository;
import com.droneservice.service.MedicationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicationServiceImpl implements MedicationService {


    @Autowired
    private MedicationRepository medRepo;

    private final ModelMapper modelMapper;

    @Autowired
    public MedicationServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    @Override
    public void createMedication(MedicationDTO medDto) {

        Medication med = modelMapper.map(medDto,  Medication.class);
        medRepo.save(med);

    }

    @Override
    public List<Medication> createMedication(List<MedicationDTO> medDtos) {

        List<Medication> meds = medDtos.stream()
                .map(entity -> modelMapper.map(entity, Medication.class))
                .collect(Collectors.toList());

        List<Medication> actualList = new ArrayList<>();
        medRepo.saveAll(meds).iterator().forEachRemaining(actualList::add);

        return actualList;

    }

    @Override
    public List<Medication> listMedications() {
        // TODO Auto-generated method stub
        return medRepo.findAll();
    }

    @Override
    public List<Medication> listMedications(List<Long> ids) {
        // TODO Auto-generated method stub
        return medRepo.findAllByIdIn(ids);
    }

}

