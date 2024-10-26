package com.InsuranceManagement.entity;
import com.InsuranceManagement.enums.PropertyType;
import jakarta.persistence.*;

@Entity
@Table(name = "insurancesHome")
public class InsuranceHome extends Insurance{

    @Column(nullable = false)
    private Double propertyValue;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PropertyType propertyType;

    @Column(nullable = false)
    private Boolean isRiskZone;

    @Column(nullable = false)
    private Boolean hasSecuritySystem;

    public Double getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(Double propertyValue) {
        this.propertyValue = propertyValue;
    }

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }

    public Boolean getIsRiskZone() {
        return isRiskZone;
    }

    public void setIsRiskZone(Boolean isRiskZone) {
        this.isRiskZone = isRiskZone;
    }

    public Boolean getHasSecuritySystem() {
        return hasSecuritySystem;
    }

    public void setHasSecuritySystem(Boolean hasSecuritySystem) {
        this.hasSecuritySystem = hasSecuritySystem;
    }

    @Override
    public String toString() {
        return "InsuranceHome{" +
                "propertyValue=" + propertyValue +
                ", propertyType=" + propertyType +
                ", isRiskZone=" + isRiskZone +
                ", hasSecuritySystem=" + hasSecuritySystem +
                '}';
    }
}
