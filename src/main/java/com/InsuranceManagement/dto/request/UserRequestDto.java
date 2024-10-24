package com.InsuranceManagement.dto.request;

import jakarta.validation.constraints.NotNull;

public record UserRequestDto(
       @NotNull String name,
       @NotNull String email,
       @NotNull String password,
       @NotNull String phoneNumber,
         String address
) {
}
