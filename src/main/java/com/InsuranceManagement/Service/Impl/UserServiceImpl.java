package com.InsuranceManagement.Service.Impl;

import com.InsuranceManagement.Repository.UserRepository;
import com.InsuranceManagement.Service.Interfaces.UserService;
import com.InsuranceManagement.dto.request.UserRequestDto;
import com.InsuranceManagement.dto.response.UserResponseDto;
import com.InsuranceManagement.entity.User;
import com.InsuranceManagement.enums.UserRole;
import com.InsuranceManagement.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    @Override
    public Boolean registerUser(UserRequestDto userRequestDto) {
        Optional<User> existingUser = userRepository.findByEmail(userRequestDto.email());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("Email is already in use.");
        }

        String encodedPassword = passwordEncoder.encode(userRequestDto.password());

        User user = User.builder()
                .name(userRequestDto.name())
                .email(userRequestDto.email())
                .phoneNumber(userRequestDto.phoneNumber())
                .address(userRequestDto.address())
                .password(encodedPassword)
                .role(UserRole.ROLE_USER)
                .build();

        User insertedUser = userRepository.save(user);

        return insertedUser.getId() != null;
    }

    @Override
    public Optional<UserResponseDto> authenticateUser(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return Optional.ofNullable(userMapper.toDto(user));
            }
        }
        return Optional.empty();
    }
}
