package com.InsuranceManagement.Service.Interfaces;

import com.InsuranceManagement.dto.request.InsuranceCarRequestDto;
import com.InsuranceManagement.dto.response.InsuranceCarResponseDto;
import com.InsuranceManagement.entity.InsuranceCar;
import com.InsuranceManagement.exeption.NotFoundException;

import java.util.List;
import java.util.UUID;

public interface InsuranceCarService {
    InsuranceCarResponseDto createInsuranceCar(InsuranceCarRequestDto requestDto) throws Exception;
    Double CalculateInsuranceCar(InsuranceCarResponseDto requestDto);
    List<InsuranceCarResponseDto> getInsuranceCarByUserId(UUID userId);
    InsuranceCarResponseDto getInsuranceCarById(UUID carId) throws Exception;
}
