package com.InsuranceManagement.Repository;

import com.InsuranceManagement.entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InsuranceCarRepository extends JpaRepository<Insurance, UUID> {
}
