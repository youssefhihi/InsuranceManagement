package com.InsuranceManagement.dto.request;

import com.InsuranceManagement.entity.User;
import com.InsuranceManagement.enums.CoverageType;

public record InsuranceHealthRequestDto(
        Integer age,
        String healthStatus,
        CoverageType coverageType,
        User user

) {
}
