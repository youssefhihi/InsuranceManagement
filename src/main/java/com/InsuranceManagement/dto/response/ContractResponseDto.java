package com.InsuranceManagement.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record ContractResponseDto(
        UUID id,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Double amount,
        Boolean cancelled,
        UUID insuranceId,
        List<String> mediaList
) {}