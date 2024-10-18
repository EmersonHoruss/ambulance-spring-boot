package com.ambulance.ambulance.auth.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ambulance.ambulance.auth.entities.Role;
import com.ambulance.ambulance.auth.entities.UserAuth;
import com.ambulance.ambulance.auth.repositories.RoleRepository;
import com.ambulance.ambulance.auth.repositories.UserAuthRepository;

@RestController
public class UserAuthController {
    @Autowired
    private UserAuthRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody UserAuth user) {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        Role guestRole = this.roleRepository
                .findByRoleName("ROLE_GUEST")
                .orElseGet(() -> this.roleRepository.save(new Role("ROLE_GUEST")));
        user.getRoles().add(guestRole);

        this.userRepository.save(user);

        return ResponseEntity.ok("UserAuth registered as a GUEST");
    }

    @GetMapping("/guest")
    public ResponseEntity<List<UserAuth>> list() {
        List<UserAuth> users = this.userRepository.findAll();
        return ResponseEntity.ok(users);
    }
}
