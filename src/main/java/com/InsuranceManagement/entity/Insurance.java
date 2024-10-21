package com.InsuranceManagement.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "insurances")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Insurance {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

}
