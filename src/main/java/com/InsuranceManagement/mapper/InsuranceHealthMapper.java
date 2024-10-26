package com.InsuranceManagement.mapper;


import com.InsuranceManagement.dto.request.InsuranceHealthRequestDto;
import com.InsuranceManagement.dto.response.InsuranceHealthResponseDto;
import com.InsuranceManagement.entity.InsuranceHealth;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InsuranceHealthMapper {

    @Mapping(target = "user", source = "user")
    InsuranceHealth toEntity(InsuranceHealthRequestDto InsuranceHealthRequestDto);

    InsuranceHealthResponseDto toDto(InsuranceHealth insuranceHealth);
}

