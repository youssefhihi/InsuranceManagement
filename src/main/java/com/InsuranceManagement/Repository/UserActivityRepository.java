package com.InsuranceManagement.Repository;

import com.InsuranceManagement.entity.UserActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserActivityRepository extends JpaRepository<UserActivity, UUID> {
    List<UserActivity> findByUser_Email(String email);
}
