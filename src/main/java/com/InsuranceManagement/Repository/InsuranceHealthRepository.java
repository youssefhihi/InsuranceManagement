package com.InsuranceManagement.Repository;

import com.InsuranceManagement.entity.InsuranceHealth;
import com.InsuranceManagement.entity.InsuranceHealth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InsuranceHealthRepository extends JpaRepository<InsuranceHealth, UUID> {
    List<InsuranceHealth> findByUserId(UUID userId);
    Optional<InsuranceHealth> findInsuranceHealthById(UUID id);
}
