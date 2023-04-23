package com.project.vehiclerental.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private int modelYear;
    private String vehicleBrand;
    private String model;
    private String licensePlate;
    private String color;
    private String mileage;

    @ManyToOne
    @JoinColumn(name = "gearingid")
    private Gearing gearing;

    public Vehicle() {

    }

    public Vehicle(int modelYear, String vehicleBrand, String model, String licensePlate, String color,
            String mileage, Gearing gearing) {
        this.modelYear = modelYear;
        this.vehicleBrand = vehicleBrand;
        this.model = model;
        this.licensePlate = licensePlate;
        this.color = color;
        this.mileage = mileage;
        this.gearing = gearing;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public void setVehicleBrand(String vehicleBrand) {
        this.vehicleBrand = vehicleBrand;
    }

    public int getModelYear() {
        return modelYear;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public Gearing getGearing() {
        return gearing;
    }

    public void setGearing(Gearing gearing) {
        this.gearing = gearing;
    }

    @Override
    public String toString() {
        return "Vehicle [id=" + id + ", modelYear=" + modelYear + ", vehicleBrand=" + vehicleBrand + ", model=" + model
                + ", licensePlate=" + licensePlate + ", color=" + color + ", mileage=" + mileage + ", gearing="
                + this.getGearing();
    }

}
