package com.InsuranceManagement.Service.Impl;

import com.InsuranceManagement.Repository.UserRepository;
import com.InsuranceManagement.Repository.UserActivityRepository;
import com.InsuranceManagement.entity.UserActivity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserActivityServiceImpl {

    private final UserActivityRepository userActivityRepository;
    private final UserRepository userRepository;

    public void logActivity(String email, String action) {
        var user = userRepository.findByEmail(email).orElse(null);

        UserActivity activity = UserActivity.builder()
                .email(email)
                .action(action)
                .timestamp(LocalDateTime.now())
                .user(user)
                .build();

        userActivityRepository.save(activity);
    }
}