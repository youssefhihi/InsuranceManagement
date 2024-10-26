package com.InsuranceManagement.dto.response;

import com.InsuranceManagement.enums.CoverageType;

import java.util.UUID;

public record InsuranceHealthResponseDto(
            UUID id,
            Integer age,
            String healthStatus,
            CoverageType coverageType
) {
}
