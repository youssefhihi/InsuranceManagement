package com.InsuranceManagement.mapper;

import com.InsuranceManagement.dto.request.UserRequestDto;
import com.InsuranceManagement.dto.response.UserResponseDto;
import com.InsuranceManagement.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
     User toEntity(UserRequestDto user);
     UserResponseDto toDto(User user);
}
