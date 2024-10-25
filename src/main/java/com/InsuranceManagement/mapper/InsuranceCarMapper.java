package com.InsuranceManagement.mapper;

import com.InsuranceManagement.dto.request.InsuranceCarRequestDto;
import com.InsuranceManagement.dto.response.InsuranceCarResponseDto;
import com.InsuranceManagement.entity.InsuranceCar;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InsuranceCarMapper {

    @Mapping(target = "user", source = "user")
    InsuranceCar toEntity(InsuranceCarRequestDto dto);

    InsuranceCarResponseDto toDto(InsuranceCar entity);
}