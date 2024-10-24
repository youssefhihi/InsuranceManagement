package com.InsuranceManagement.Service.Interfaces;

import com.InsuranceManagement.dto.request.InsuranceCarRequestDto;
import com.InsuranceManagement.dto.response.InsuranceCarResponseDto;
import com.InsuranceManagement.entity.InsuranceCar;

public interface InsuranceCarService {
    InsuranceCarResponseDto createInsuranceCar(InsuranceCarRequestDto requestDto);
    InsuranceCarResponseDto CalculateInsuranceCar(InsuranceCarRequestDto requestDto);
}
