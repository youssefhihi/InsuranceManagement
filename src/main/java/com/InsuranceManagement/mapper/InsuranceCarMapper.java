package com.InsuranceManagement.mapper;

import com.InsuranceManagement.dto.request.InsuranceCarRequestDto;
import com.InsuranceManagement.dto.response.InsuranceCarResponseDto;
import com.InsuranceManagement.entity.InsuranceCar;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InsuranceCarMapper {

    InsuranceCar toEntity(InsuranceCarRequestDto dto);

    InsuranceCarResponseDto toDto(InsuranceCar entity);
}