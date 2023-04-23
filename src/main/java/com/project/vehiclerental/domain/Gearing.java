package com.project.vehiclerental.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Gearing {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long gearingid;
    private String name;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gearing")
    private List<Vehicle> vehicles;

    public Gearing() {
    }

    public Gearing(String name) {
        super();
        this.name = name;
    }

    public Long getGearingid() {
        return gearingid;
    }

    public void setGearingid(Long gearingid) {
        this.gearingid = gearingid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Gearing [gearingid=" + gearingid + ", name=" + name + "]";
    }

}