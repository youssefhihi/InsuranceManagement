package com.InsuranceManagement.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "estimates")
public class Estimate {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID )
    UUID id;

    @Column(nullable = false)
    Integer amount;

    @Column(nullable = false)
    LocalDateTime requestDate;

    @Column(nullable = false)
    Boolean isAccepted;




}
