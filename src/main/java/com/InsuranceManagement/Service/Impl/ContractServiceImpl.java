package com.InsuranceManagement.Service.Impl;

import com.InsuranceManagement.Repository.ContractRepository;
import com.InsuranceManagement.Service.Interfaces.ContractService;
import com.InsuranceManagement.entity.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractServiceImpl implements ContractService {
    private final ContractRepository contractRepository;

    @Autowired
    public ContractServiceImpl(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    @Override
    public Boolean createContract(Contract contract) {
      Contract createdContract = contractRepository.save(contract);
     return createdContract.getId() != null;
    }

}
