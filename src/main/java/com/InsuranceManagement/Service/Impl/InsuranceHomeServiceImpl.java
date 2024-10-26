package com.InsuranceManagement.Service.Impl;

import com.InsuranceManagement.Repository.InsuranceHomeRepository;
import com.InsuranceManagement.Repository.UserRepository;
import com.InsuranceManagement.Service.Interfaces.InsuranceHomeService;
import com.InsuranceManagement.dto.request.InsuranceHomeRequestDto;
import com.InsuranceManagement.dto.response.InsuranceHomeResponseDto;
import com.InsuranceManagement.entity.InsuranceHome;
import com.InsuranceManagement.entity.User;
import com.InsuranceManagement.enums.PropertyType;
import com.InsuranceManagement.mapper.InsuranceHomeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InsuranceHomeServiceImpl implements InsuranceHomeService {
    private final InsuranceHomeRepository insuranceHomeRepository;
    private final InsuranceHomeMapper insuranceHomeMapper;
    private final UserRepository userRepository;

    @Autowired
    public InsuranceHomeServiceImpl(InsuranceHomeRepository insuranceHomeRepository, InsuranceHomeMapper insuranceHomeMapper, UserRepository userRepository) {
        this.insuranceHomeRepository = insuranceHomeRepository;
        this.insuranceHomeMapper = insuranceHomeMapper;
        this.userRepository = userRepository;
    }

    public InsuranceHomeResponseDto createInsuranceHome(InsuranceHomeRequestDto requestDto) throws Exception {
        User user = userRepository.findById(requestDto.user().getId())
                .orElseThrow(() -> new Exception("User not found"));

        InsuranceHome insuranceHome = insuranceHomeMapper.toEntity(requestDto);
        insuranceHome.setUser(user);
        System.out.println("service request " + requestDto);
        System.out.println("service " + insuranceHome.toString());

        InsuranceHome savedInsuranceHome = insuranceHomeRepository.save(insuranceHome);

        return insuranceHomeMapper.toDto(savedInsuranceHome);
    }

    @Override
    public Double CalculateInsuranceHome(InsuranceHomeResponseDto insuranceHome) {
        double amount = 300 ;

        if (insuranceHome.isRiskZone()) {
            amount *= 1.05;
        }

        if (insuranceHome.propertyType().equals(PropertyType.HOUSE)) {
            amount *= 1.02;
        }

        if (insuranceHome.propertyValue() > 200000.0) {
            amount *= 1.1;
        }

        if(insuranceHome.hasSecuritySystem()){
            amount *= 0.85;
        }else{
            amount *= 1.15;
        }

        return amount;
    }

    @Override
    public List<InsuranceHomeResponseDto> getInsuranceHomeByUserId(UUID userId) {

        List<InsuranceHome> insuranceHomes = insuranceHomeRepository.findByUserId(userId);


        return insuranceHomes.stream().map(insuranceHomeMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public InsuranceHomeResponseDto getInsuranceHomeById(UUID Id) throws Exception {

        Optional<InsuranceHome> insuranceHome = insuranceHomeRepository.findInsuranceHomeById(Id);
        if (insuranceHome.isPresent()) {
            return insuranceHomeMapper.toDto(insuranceHome.get());
        }else {
            throw new Exception("insurance Not found");
        }
    }
}
