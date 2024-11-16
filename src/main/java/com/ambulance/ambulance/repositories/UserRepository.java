package com.ambulance.ambulance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.ambulance.ambulance.auth.entities.UserAuth;

@Repository
public interface UserRepository extends JpaRepository<UserAuth, Long>, JpaSpecificationExecutor<UserAuth> {
}
