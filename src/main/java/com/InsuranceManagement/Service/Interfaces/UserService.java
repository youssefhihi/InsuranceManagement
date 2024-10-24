package com.InsuranceManagement.Service.Interfaces;

import com.InsuranceManagement.dto.request.UserRequestDto;
import com.InsuranceManagement.dto.response.UserResponseDto;

import java.util.Optional;


public interface UserService {
    Boolean registerUser(UserRequestDto userRequestDto);
    Optional<UserResponseDto> authenticateUser(String email, String password);
}
