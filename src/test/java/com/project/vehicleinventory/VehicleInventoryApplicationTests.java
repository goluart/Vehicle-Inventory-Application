package com.project.vehicleinventory;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

import com.project.vehicleinventory.domain.UserRepository;
import com.project.vehicleinventory.web.VehicleController;

@SpringBootTest
class VehicleInventoryApplicationTests {

	@Autowired
	private VehicleController vcontroller;

	@Autowired
	private UserRepository ucontroller;
	
	@Test
	void contextLoads() throws Exception {
		assertThat(vcontroller).isNotNull();
		assertThat(ucontroller).isNotNull();
	}

}
