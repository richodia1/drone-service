package com.droneservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DroneServiceApplicationTests {

	@Test
	void contextLoads() {
		int a = 2;
		assertThat(a = 1+1);
	}

}
