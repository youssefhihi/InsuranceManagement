package com.InsuranceManagement.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "insurancesCar")
public class InsuranceCar extends Insurance {

    Integer driverAge;

    String  model;

    String brand;

    Boolean usage;

    String drivingHistory;
}
