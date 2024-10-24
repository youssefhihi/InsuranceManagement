package com.InsuranceManagement.Service.Impl;

import com.InsuranceManagement.Repository.InsuranceCarRepository;
import com.InsuranceManagement.Service.Interfaces.InsuranceCarService;
import com.InsuranceManagement.dto.request.InsuranceCarRequestDto;
import com.InsuranceManagement.dto.response.InsuranceCarResponseDto;
import com.InsuranceManagement.entity.InsuranceCar;
import com.InsuranceManagement.enums.CarType;
import com.InsuranceManagement.mapper.InsuranceCarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InsuranceCarServiceImpl implements InsuranceCarService {

    private final InsuranceCarRepository insuranceCarRepository;
    private final InsuranceCarMapper insuranceCarMapper;

    @Autowired
    public InsuranceCarServiceImpl(InsuranceCarRepository insuranceCarRepository, InsuranceCarMapper insuranceCarMapper) {
        this.insuranceCarRepository = insuranceCarRepository;
        this.insuranceCarMapper = insuranceCarMapper;
    }

    public InsuranceCarResponseDto createInsuranceCar(InsuranceCarRequestDto requestDto) {
        InsuranceCar savedInsuranceCar = insuranceCarRepository.save(insuranceCarMapper.toEntity(requestDto));

        return insuranceCarMapper.toDto(savedInsuranceCar);
    }

    @Override
    public InsuranceCarResponseDto CalculateInsuranceCar(InsuranceCarRequestDto requestDto) {
        InsuranceCar insuranceCar = insuranceCarMapper.toEntity(requestDto);



        double amount = 500.0;

        if (insuranceCar.getDriverAge() < 25) {
            amount *= 1.10;
        }

        if (insuranceCar.getCarType().equals(CarType.luxury)) {
            amount *= 1.15;
        }

        if (Boolean.TRUE.equals(insuranceCar.getUsage())) {
            amount *= 1.10;
        }

        if (Boolean.TRUE.equals(insuranceCar.getHadSinister())) {
            amount *= 1.10;
        } else {
            amount *= 0.80;
        }

        InsuranceCarResponseDto responseDto = insuranceCarMapper.toDto(insuranceCar);
        responseDto = new InsuranceCarResponseDto(
                responseDto.id(),
                responseDto.driverAge(),
                responseDto.model(),
                responseDto.brand(),
                responseDto.usage(),
                responseDto.carType(),
                responseDto.hadSinister(),
                amount
        );


        return responseDto;
    }



}
