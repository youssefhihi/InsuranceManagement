package com.InsuranceManagement.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "insurancesHome")
public class InsuranceHome extends Insurance{

    @Column(nullable = false)
    String propertyValue;

    @Column(nullable = false)
    String housingType;

    @Column(nullable = false)
    String location;

    @Column(nullable = false)
    String securitySystem;


}
