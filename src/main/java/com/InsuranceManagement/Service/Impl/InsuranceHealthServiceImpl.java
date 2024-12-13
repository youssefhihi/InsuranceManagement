package com.InsuranceManagement.Service.Impl;

import com.InsuranceManagement.Repository.InsuranceHealthRepository;
import com.InsuranceManagement.Repository.UserRepository;
import com.InsuranceManagement.Service.Interfaces.InsuranceHealthService;
import com.InsuranceManagement.dto.request.InsuranceHealthRequestDto;
import com.InsuranceManagement.dto.response.InsuranceHealthResponseDto;
import com.InsuranceManagement.entity.InsuranceHealth;
import com.InsuranceManagement.entity.User;
import com.InsuranceManagement.enums.CoverageType;
import com.InsuranceManagement.mapper.InsuranceHealthMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InsuranceHealthServiceImpl implements InsuranceHealthService {
    private final InsuranceHealthRepository insuranceHealthRepository;
    private final InsuranceHealthMapper insuranceHealthMapper;
    private final UserRepository userRepository;

    @Autowired
    public InsuranceHealthServiceImpl(InsuranceHealthRepository insuranceHealthRepository, InsuranceHealthMapper insuranceHealthMapper, UserRepository userRepository) {
        this.insuranceHealthRepository = insuranceHealthRepository;
        this.insuranceHealthMapper = insuranceHealthMapper;
        this.userRepository = userRepository;
    }

    public InsuranceHealthResponseDto createInsuranceHealth(InsuranceHealthRequestDto requestDto) throws Exception {
        User user = userRepository.findById(requestDto.user().getId())
                .orElseThrow(() -> new Exception("User not found"));

        InsuranceHealth insuranceHealth = insuranceHealthMapper.toEntity(requestDto);
        insuranceHealth.setUser(user);
        System.out.println("service request " + requestDto);
        System.out.println("service " + insuranceHealth.toString());

        InsuranceHealth savedInsuranceHealth = insuranceHealthRepository.save(insuranceHealth);

        return insuranceHealthMapper.toDto(savedInsuranceHealth);
    }

    @Override
    public Double CalculateInsuranceHealth(InsuranceHealthResponseDto insuranceHealth) {
        double amount = 150;

        if (insuranceHealth.age() > 60) {
            amount *= 1.2;
        }

        if (insuranceHealth.coverageType().equals(CoverageType.basic)) {
            amount *= 0.9;
        }else{
            amount *= 1.05;
        }

        if (insuranceHealth.healthStatus().contains("chronic diseases")) {
            amount *= 1.3;
        }

        return amount;
    }

    @Override
    public List<InsuranceHealthResponseDto> getInsuranceHealthByUserId(String email) {

        User user = userRepository.findByEmail(email).orElseThrow();
        List<InsuranceHealth> insuranceHealths = insuranceHealthRepository.findByUserId(user.getId());


        return insuranceHealths.stream().map(insuranceHealthMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public InsuranceHealthResponseDto getInsuranceHealthById(UUID Id) throws Exception {

        Optional<InsuranceHealth> insuranceHealth = insuranceHealthRepository.findInsuranceHealthById(Id);
        if (insuranceHealth.isPresent()) {
            return insuranceHealthMapper.toDto(insuranceHealth.get());
        }else {
            throw new Exception("insurance Not found");
        }
    }


}
