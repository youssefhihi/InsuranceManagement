package com.InsuranceManagement.dto.response;

import com.InsuranceManagement.enums.CarType;

import java.util.UUID;

public record InsuranceCarResponseDto(
        UUID id,
        Integer driverAge,
        String model,
        String brand,
        Boolean usage,
        CarType carType,
        Boolean hadSinister
) {}
