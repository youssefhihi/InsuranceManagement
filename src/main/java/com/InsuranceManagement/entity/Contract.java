package com.InsuranceManagement.entity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "contracts")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(nullable = false)
    LocalDateTime startDate;

    @Column(nullable = false)
    LocalDateTime endDate;

    @Column(nullable = false)
    Double amount;

    @Column(nullable = false)
    Boolean cancelled;


    @OneToOne
    @JoinColumn(name = "insurance_id", nullable = false)
    private Insurance insurance;




}
