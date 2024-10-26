package com.InsuranceManagement.Repository;

import com.InsuranceManagement.entity.Insurance;
import com.InsuranceManagement.entity.InsuranceCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface InsuranceCarRepository extends JpaRepository<Insurance, UUID> {
    List<InsuranceCar> findByUserId(UUID userId);
    Optional<InsuranceCar> findInsuranceCarById(UUID id);
}
