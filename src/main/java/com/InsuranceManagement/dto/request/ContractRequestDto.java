package com.InsuranceManagement.dto.request;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record ContractRequestDto(
        UUID insuranceId,
        Double amount,
        LocalDateTime endDate,
        List<String> documents
) {}