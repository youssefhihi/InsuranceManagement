package com.InsuranceManagement.Service.Interfaces;

import com.InsuranceManagement.dto.request.InsuranceHealthRequestDto;
import com.InsuranceManagement.dto.response.InsuranceHealthResponseDto;

import java.util.List;
import java.util.UUID;

public interface InsuranceHealthService {
    InsuranceHealthResponseDto createInsuranceHealth(InsuranceHealthRequestDto requestDto) throws Exception;
    Double CalculateInsuranceHealth(InsuranceHealthResponseDto requestDto);
    List<InsuranceHealthResponseDto> getInsuranceHealthByUserId(String email);
    InsuranceHealthResponseDto getInsuranceHealthById(UUID carId) throws Exception;
}
