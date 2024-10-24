package com.InsuranceManagement.mapper;

import com.InsuranceManagement.dto.request.ContractRequestDto;
import com.InsuranceManagement.dto.response.ContractResponseDto;
import com.InsuranceManagement.entity.Contract;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContractMapper {

    Contract toEntity(ContractRequestDto dto);

    ContractResponseDto toDto(Contract entity);
}
