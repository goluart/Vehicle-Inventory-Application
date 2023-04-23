package com.project.vehicleinventory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.project.vehicleinventory.domain.Gearing;
import com.project.vehicleinventory.domain.GearingRepository;
import com.project.vehicleinventory.domain.User;
import com.project.vehicleinventory.domain.UserRepository;
import com.project.vehicleinventory.domain.Vehicle;
import com.project.vehicleinventory.domain.VehicleRepository;

@SpringBootApplication
public class VehicleInventoryApplication {
	private static final Logger log = LoggerFactory.getLogger(VehicleInventoryApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(VehicleInventoryApplication.class, args);
	}

	@Bean
	public CommandLineRunner vehicleDemo(VehicleRepository vrepository, GearingRepository grepository, UserRepository urepository) {
		return (args) -> {
			log.info("save some vehicles");
			grepository.save(new Gearing("Automatic"));
			grepository.save(new Gearing("Manual"));

			vrepository.save(new Vehicle(1990, "Audi", "A4", "ABC-321", "red", "556 555", grepository.findByName("Manual").get(0)));
			vrepository.save(new Vehicle(2015, "BMW", "M5", "DFA-221", "black", "111 555", grepository.findByName("Automatic").get(0)));

			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "user.user@user.com", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "admin@admin.com", "ADMIN");
			User user3 = new User("hhelia2023", "$2a$10$XhDmyJTI4MSa3dgf1FGhdum11VZCOBhlwS5PN10xkm4./3aiSlHRq", "hhelia@haaga-helia.fi", "USER"); // hhelia2023/haagahelia
			urepository.save(user1);
			urepository.save(user2);
			urepository.save(user3);

			log.info("feth all vehicles");
			for (Vehicle vehicle : vrepository.findAll()) {
				log.info(vehicle.toString());
			}
		};
	}

}
