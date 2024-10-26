package com.InsuranceManagement.dto.request;

import com.InsuranceManagement.entity.User;
import com.InsuranceManagement.enums.PropertyType;
import jakarta.validation.constraints.NotNull;

public record InsuranceHomeRequestDto(
        @NotNull Double propertyValue,
        @NotNull PropertyType propertyType,
        @NotNull Boolean isRiskZone,
        @NotNull Boolean hasSecuritySystem,
        User user
) {
}
