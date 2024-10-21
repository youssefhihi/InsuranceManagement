package com.InsuranceManagement.Service.Impl;

import com.InsuranceManagement.Repository.UserRepository;
import com.InsuranceManagement.Service.Interfaces.UserService;
import com.InsuranceManagement.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
