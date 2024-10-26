package com.InsuranceManagement.Service.Interfaces;

import com.InsuranceManagement.dto.request.InsuranceHomeRequestDto;
import com.InsuranceManagement.dto.response.InsuranceHomeResponseDto;

import java.util.List;
import java.util.UUID;

public interface InsuranceHomeService {
    InsuranceHomeResponseDto createInsuranceHome(InsuranceHomeRequestDto requestDto) throws Exception;
    Double CalculateInsuranceHome(InsuranceHomeResponseDto requestDto);
    List<InsuranceHomeResponseDto> getInsuranceHomeByUserId(UUID userId);
    InsuranceHomeResponseDto getInsuranceHomeById(UUID carId) throws Exception;
}
