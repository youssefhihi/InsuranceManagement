package com.InsuranceManagement.entity;
import com.InsuranceManagement.enums.CarType;
import jakarta.persistence.*;

@Entity
@Table(name = "insurancesCar")
public class InsuranceCar extends Insurance {

    Integer driverAge;

    String  model;

    String brand;

    Boolean usage;

    Boolean hadSinister;

    @Enumerated(EnumType.STRING)
    CarType carType;

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public Integer getDriverAge() {
        return driverAge;
    }

    public void setDriverAge(Integer driverAge) {
        this.driverAge = driverAge;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Boolean getUsage() {
        return usage;
    }

    public void setUsage(Boolean usage) {
        this.usage = usage;
    }

    public Boolean getHadSinister() {
        return hadSinister;
    }

    public void setHadSinister(Boolean hadSinister) {
        this.hadSinister = hadSinister;
    }

}
