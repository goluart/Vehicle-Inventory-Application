package com.project.vehiclerental.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface GearingRepository extends CrudRepository<Gearing, Long> {
    List<Gearing> findByName(String name);
}
