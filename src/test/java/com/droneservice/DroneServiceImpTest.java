package com.droneservice;

import com.droneservice.controller.Response;
import com.droneservice.dto.AvailableDronesDTO;
import com.droneservice.dto.DroneDTO;
import com.droneservice.dto.LoadDroneDTO;
import com.droneservice.model.DroneModel;
import com.droneservice.service.impl.DroneServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
/*
a proof of concept that Richard Okosodo can write Unit test in a TDD environment.
 */
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DroneServiceImpTest {
    @Autowired
    private DroneServiceImpl droneService;


    private DroneDTO droneDTO;

    @BeforeAll
    public void setup() {
        droneDTO = new DroneDTO();
        droneDTO.setBatteryLife(100);
        droneDTO.setDroneModel(DroneModel.Heavyweight);
        droneDTO.setMaxWeight(500.0);
        droneDTO.setSerialNumber("110110TEST");



    }

    @Test
    void test_whenregisterdrone_returnsdto() {

        DroneDTO _dto = droneService.registerDrone(droneDTO);
        Assertions.assertNotNull(_dto.getSerialNumber());

    }

    @Test
    void when_given_serialnumber_returns_droneDTO() {

        droneDTO = new DroneDTO();
        droneDTO.setBatteryLife(100);
        droneDTO.setDroneModel(DroneModel.Heavyweight);
        droneDTO.setMaxWeight(500.0);
        droneDTO.setSerialNumber("110110TEST2");


        droneService.registerDrone(droneDTO);
        DroneDTO dto = droneService.getDrone("110110TEST2");
        assertThat(dto.getSerialNumber()).isEqualTo(droneDTO.getSerialNumber());
    }

}
