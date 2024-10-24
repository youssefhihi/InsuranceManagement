package com.InsuranceManagement.dto.response;

import com.InsuranceManagement.entity.Insurance;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record ContractResponseDto(
       @NotNull  UUID id,
       @NotNull  LocalDateTime startDate,
       @NotNull  LocalDateTime endDate,
       @NotNull  Double amount,
       @NotNull  Boolean cancelled,
       @NotNull  Insurance insurance
) {
}
