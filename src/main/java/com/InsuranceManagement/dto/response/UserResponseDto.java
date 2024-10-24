package com.InsuranceManagement.dto.response;

import java.util.UUID;

public record UserResponseDto (
        UUID id,
        String name,
        String email,
        String phoneNumber,
        String address
){
}
