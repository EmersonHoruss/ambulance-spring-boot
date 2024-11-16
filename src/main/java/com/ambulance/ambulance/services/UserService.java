package com.ambulance.ambulance.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ambulance.ambulance.auth.entities.UserAuth;
import com.ambulance.ambulance.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    protected UserRepository userRepository;

    public UserAuth get(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
    }

    public Page<UserAuth> get(Specification<UserAuth> specification, Pageable pageable) {
        return userRepository.findAll(specification, pageable);
    }
}
