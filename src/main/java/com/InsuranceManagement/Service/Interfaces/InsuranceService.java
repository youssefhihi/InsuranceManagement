package com.InsuranceManagement.Service.Interfaces;

import com.InsuranceManagement.entity.Insurance;

import java.util.UUID;

public interface InsuranceService {
    Insurance getInsurance(UUID id) throws Exception;
}
