package com.InsuranceManagement.Service.Impl;

import com.InsuranceManagement.Repository.InsuranceRepository;
import com.InsuranceManagement.Repository.UserRepository;
import com.InsuranceManagement.Service.Interfaces.InsuranceService;
import com.InsuranceManagement.entity.Insurance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class InsuranceServiceImpl implements InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final UserRepository userRepository;

    @Autowired
    public InsuranceServiceImpl(InsuranceRepository insuranceRepository,  UserRepository userRepository) {
        this.insuranceRepository = insuranceRepository;
        this.userRepository = userRepository;
    }


    @Override
    public Insurance getInsurance(UUID id) throws Exception {
       Optional<Insurance> insurance = insuranceRepository.findById(id);
       if(insurance.isPresent()){
           return insurance.get();
       }else{
           throw new  Exception("insurance not found");
       }
    }
}
