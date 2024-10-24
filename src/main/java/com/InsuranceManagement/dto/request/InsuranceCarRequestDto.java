package com.InsuranceManagement.dto.request;

import com.InsuranceManagement.enums.CarType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record InsuranceCarRequestDto(
        @NotNull @Positive Integer driverAge,
        @NotNull @Size(min = 2, max = 100) String model,
        @NotNull @Size(min = 2, max = 100) String brand,
        @NotNull Boolean usage,
        @NotNull CarType carType,
        @NotNull Boolean hadSinister
) {}
