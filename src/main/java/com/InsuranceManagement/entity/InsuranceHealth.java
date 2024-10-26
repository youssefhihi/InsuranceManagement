package com.InsuranceManagement.entity;
import com.InsuranceManagement.enums.CoverageType;
import jakarta.persistence.*;

@Entity
@Table(name = "insurancesHealth")
public class InsuranceHealth extends Insurance {

    @Column(nullable = false)
    private Integer age ;

    @Column(nullable = false)
    private String healthStatus ;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CoverageType coverageType ;


    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public CoverageType getCoverageType() {
        return coverageType;
    }

    public void setCoverageType(CoverageType coverageType) {
        this.coverageType = coverageType;
    }

    @Override
    public String toString() {
        return "InsuranceHealth{" +
                "age=" + age +
                ", healthStatus='" + healthStatus + '\'' +
                ", coverageType=" + coverageType +
                ", user=" + user +
                '}';
    }
}
