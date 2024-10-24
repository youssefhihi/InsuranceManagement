package com.InsuranceManagement.Service.Impl;

import com.InsuranceManagement.Repository.ContractRepository;
import com.InsuranceManagement.Service.Interfaces.ContractService;
import com.InsuranceManagement.mapper.ContractMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractServiceImpl implements ContractService {
    private final ContractRepository contractRepository;
    private final ContractMapper contractMapper;

    @Autowired
    public ContractServiceImpl(ContractRepository contractRepository, ContractMapper contractMapper) {
        this.contractRepository = contractRepository;
        this.contractMapper = contractMapper;
    }


}
