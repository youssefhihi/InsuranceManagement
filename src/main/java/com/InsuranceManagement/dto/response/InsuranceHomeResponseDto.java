package com.InsuranceManagement.dto.response;

import com.InsuranceManagement.enums.PropertyType;

import java.util.UUID;

public record InsuranceHomeResponseDto(
        UUID id,
        Double propertyValue,
        PropertyType propertyType,
        Boolean isRiskZone,
        Boolean hasSecuritySystem
) {
}
