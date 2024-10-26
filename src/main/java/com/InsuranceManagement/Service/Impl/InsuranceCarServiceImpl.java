package com.InsuranceManagement.Service.Impl;

import com.InsuranceManagement.Repository.InsuranceCarRepository;
import com.InsuranceManagement.Repository.UserRepository;
import com.InsuranceManagement.Service.Interfaces.InsuranceCarService;
import com.InsuranceManagement.Service.Interfaces.UserService;
import com.InsuranceManagement.dto.request.InsuranceCarRequestDto;
import com.InsuranceManagement.dto.response.InsuranceCarResponseDto;
import com.InsuranceManagement.entity.InsuranceCar;
import com.InsuranceManagement.entity.User;
import com.InsuranceManagement.enums.CarType;
import com.InsuranceManagement.exeption.NotFoundException;
import com.InsuranceManagement.mapper.InsuranceCarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InsuranceCarServiceImpl implements InsuranceCarService {

    private final InsuranceCarRepository insuranceCarRepository;
    private final InsuranceCarMapper insuranceCarMapper;
    private final UserRepository userRepository;

    @Autowired
    public InsuranceCarServiceImpl(InsuranceCarRepository insuranceCarRepository, InsuranceCarMapper insuranceCarMapper, UserRepository userRepository) {
        this.insuranceCarRepository = insuranceCarRepository;
        this.insuranceCarMapper = insuranceCarMapper;
        this.userRepository = userRepository;
    }

    public InsuranceCarResponseDto createInsuranceCar(InsuranceCarRequestDto requestDto) throws Exception {
        User user = userRepository.findById(requestDto.user().getId())
                .orElseThrow(() -> new Exception("User not found"));

        InsuranceCar insuranceCar = insuranceCarMapper.toEntity(requestDto);
        insuranceCar.setUser(user);

        InsuranceCar savedInsuranceCar = insuranceCarRepository.save(insuranceCar);

        return insuranceCarMapper.toDto(savedInsuranceCar);
    }

    @Override
    public Double CalculateInsuranceCar(InsuranceCarResponseDto insuranceCar) {
        double amount = 500.0;

        if (insuranceCar.driverAge() < 25) {
            amount *= 1.10;
        }

        if (insuranceCar.carType().equals(CarType.luxury)) {
            amount *= 1.15;
        }

        if (Boolean.TRUE.equals(insuranceCar.usage())) {
            amount *= 1.10;
        }

        if (Boolean.TRUE.equals(insuranceCar.hadSinister())) {
            amount *= 1.10;
        } else {
            amount *= 0.80;
        }

        return amount;
    }

    @Override
    public List<InsuranceCarResponseDto> getInsuranceCarByUserId(UUID userId) {

        List<InsuranceCar> insuranceCars = insuranceCarRepository.findByUserId(userId);


        return insuranceCars.stream().map(insuranceCarMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public InsuranceCarResponseDto getInsuranceCarById(UUID Id) throws Exception {

        Optional<InsuranceCar> insuranceCar = insuranceCarRepository.findInsuranceCarById(Id);
        if (insuranceCar.isPresent()) {
            return insuranceCarMapper.toDto(insuranceCar.get());
        }else {
            throw new Exception("insurance Not found");
        }
    }




}
