package com.project.vehicleinventory.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface VehicleRepository extends CrudRepository<Vehicle, Long> {
    List<Vehicle> findBylicensePlate(String licensePlate);
}