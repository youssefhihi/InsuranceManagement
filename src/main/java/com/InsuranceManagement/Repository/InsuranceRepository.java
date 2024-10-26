package com.InsuranceManagement.Repository;

import com.InsuranceManagement.entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface  InsuranceRepository extends JpaRepository<Insurance, UUID> {
    Optional<Insurance> findById(UUID id);
}
