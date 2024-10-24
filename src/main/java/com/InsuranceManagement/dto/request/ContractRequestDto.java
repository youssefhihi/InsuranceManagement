package com.InsuranceManagement.dto.request;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.UUID;

public record ContractRequestDto(
         UUID insuranceId,
        @NotNull LocalDateTime endDate,
        @NotNull Double amount
) {
}
