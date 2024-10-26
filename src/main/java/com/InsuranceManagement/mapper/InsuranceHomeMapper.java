package com.InsuranceManagement.mapper;

import com.InsuranceManagement.dto.request.InsuranceHomeRequestDto;
import com.InsuranceManagement.dto.response.InsuranceHomeResponseDto;
import com.InsuranceManagement.entity.InsuranceHome;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InsuranceHomeMapper {

    @Mapping(target = "user", source = "user")
    InsuranceHome toEntity(InsuranceHomeRequestDto InsuranceHomeRequestDto);

    InsuranceHomeResponseDto toDto(InsuranceHome insuranceHome);
}
