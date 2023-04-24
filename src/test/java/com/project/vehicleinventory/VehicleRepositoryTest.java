package com.project.vehicleinventory;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

import com.project.vehicleinventory.domain.GearingRepository;
import com.project.vehicleinventory.domain.Vehicle;
import com.project.vehicleinventory.domain.VehicleRepository;

@DataJpaTest
public class VehicleRepositoryTest {
    @Autowired
    private VehicleRepository vrepository;
    @Autowired
    private GearingRepository grepository;

    @Test
    public void findByLicensePlateShouldReturnCar() {
        List<Vehicle> vehicles = vrepository.findBylicensePlate("ABC-321");

        assertThat(vehicles).hasSize(1);
        assertThat(vehicles.get(0).getLicensePlate()).isEqualTo("ABC-321");
    }

    @Test
    public void createVehicle() {
        Vehicle vehicle = new Vehicle(1990, "Honda", "Civic", "CBA-552", "Red", "152 592km", grepository.findByName("Automatic").get(0));

        vrepository.save(vehicle);
        assertThat(vehicle.getId()).isNotNull();
    }
    
    @Test
    public void deleteVehicle() {
        vrepository.delete(vrepository.findBylicensePlate("ABC-321").get(0));
        List<Vehicle> vehicles = vrepository.findBylicensePlate("ABC-321");
        assertThat(vehicles).hasSize(0);
    }
}
