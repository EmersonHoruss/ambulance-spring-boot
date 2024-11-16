package com.ambulance.ambulance.auth.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ambulance.ambulance.auth.entities.UserAuth;

public interface UserAuthRepository extends JpaRepository<UserAuth, Long> {
    Optional<UserAuth> findByEmail(String email);
}
