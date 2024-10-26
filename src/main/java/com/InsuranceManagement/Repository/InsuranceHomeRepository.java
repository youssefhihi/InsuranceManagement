package com.InsuranceManagement.Repository;

import com.InsuranceManagement.entity.InsuranceHome;
import com.InsuranceManagement.entity.InsuranceHome;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InsuranceHomeRepository extends JpaRepository<InsuranceHome, UUID> {
    List<InsuranceHome> findByUserId(UUID userId);
    Optional<InsuranceHome> findInsuranceHomeById(UUID id);
}
