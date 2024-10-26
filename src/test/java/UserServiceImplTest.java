package com.InsuranceManagement.Service.Impl;

import com.InsuranceManagement.Repository.UserRepository;
import com.InsuranceManagement.Service.Interfaces.UserService;
import com.InsuranceManagement.dto.request.UserRequestDto;
import com.InsuranceManagement.dto.response.UserResponseDto;
import com.InsuranceManagement.entity.User;
import com.InsuranceManagement.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    private UserRequestDto userRequestDto;
    private User user;

    @BeforeEach
    void setUp() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userRequestDto = new UserRequestDto("John Doe", "john@example.com", "password123", "1234567890", "123 Street");
        user = new User("John Doe", "john@example.com", passwordEncoder.encode("password123"), "1234567890", "123 Street");
        user.setId(UUID.randomUUID());
    }


    @Test
    void testRegisterUser_Success() {
        when(userRepository.findByEmail(userRequestDto.email())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(user);

        Boolean result = userService.registerUser(userRequestDto);

        assertEquals(true, result, "User should be registered successfully.");
        verify(userRepository, times(1)).findByEmail(userRequestDto.email());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testRegisterUser_EmailAlreadyExists() {
        when(userRepository.findByEmail(userRequestDto.email())).thenReturn(Optional.of(user));
        assertEquals("Email is already in use.", assertThrows(IllegalArgumentException.class, () -> userService.registerUser(userRequestDto)).getMessage());
        verify(userRepository,times(1)).findByEmail(userRequestDto.email());
        verify(userRepository, times(0)).save(any(User.class));
    }


    @Test
    void testAuthenticateUser_Success() {
        UserResponseDto userResponseDto = new UserResponseDto(user.getId(), user.getName(), user.getEmail(), user.getPhoneNumber(), user.getAddress());
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        when(userMapper.toDto(user)).thenReturn(userResponseDto);

        Optional<UserResponseDto> result = userService.authenticateUser(user.getEmail(), "password123");

        assertTrue(result.isPresent());
        assertEquals(userResponseDto, result.get());
        verify(userRepository, times(1)).findByEmail(user.getEmail());
        verify(userMapper,times(1)).toDto(user);
    }

    @Test
    void testAuthenticateUser_InvalidCredentials() {
        String email = "john@example.com";
        String rawPassword = "wrongPassword";
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        Optional<UserResponseDto> result = userService.authenticateUser(email, rawPassword);

        assertFalse(result.isPresent());
        verify(userRepository,times(1)).findByEmail(email);
    }

    @Test
    void testAuthenticateUser_UserNotFound() {
        String email = "john@example.com";
        String rawPassword = "password123";
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        Optional<UserResponseDto> result = userService.authenticateUser(email, rawPassword);

        assertFalse(result.isPresent());
        verify(userRepository,times(1)).findByEmail(email);
    }
}
