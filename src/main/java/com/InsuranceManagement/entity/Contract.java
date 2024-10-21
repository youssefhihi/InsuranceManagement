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

    LocalDateTime startDate;

    LocalDateTime endDate;
}
