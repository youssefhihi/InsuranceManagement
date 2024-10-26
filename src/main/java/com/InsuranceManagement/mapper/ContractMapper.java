package com.InsuranceManagement.mapper;

import com.InsuranceManagement.dto.request.ContractRequestDto;
import com.InsuranceManagement.dto.response.ContractResponseDto;
import com.InsuranceManagement.entity.Contract;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContractMapper {

    @Mapping(target = "insurance.id", source = "insuranceId")
    @Mapping(target = "startDate", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "cancelled", constant = "false")
    Contract toEntity(ContractRequestDto dto);

    @Mapping(target = "insuranceId", source = "insurance.id")
    @Mapping(target = "mediaList", expression = "java(contract.getMediaList().stream().map(media -> media.getDocument()).toList())")
    ContractResponseDto toDto(Contract contract);
}