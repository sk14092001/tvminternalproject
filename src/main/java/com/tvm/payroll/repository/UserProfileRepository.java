package com.tvm.payroll.repository;

import com.tvm.payroll.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    List<UserProfile> findAllByUsername(String username);
    Optional<UserProfile> findByUsername(String username);
}
