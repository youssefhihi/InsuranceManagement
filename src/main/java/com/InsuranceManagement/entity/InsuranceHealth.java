package com.InsuranceManagement.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "insurancesHealth")
public class InsuranceHealth extends Insurance {

    @Column(nullable = false)
    Integer age ;

    @Column(nullable = false)
    String healthStatus ;

    @Column(nullable = false)
    String coverageType ;


}
