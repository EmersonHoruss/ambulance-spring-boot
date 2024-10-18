package com.ambulance.ambulance.auth.services;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ambulance.ambulance.auth.entities.UserAuth;
import com.ambulance.ambulance.auth.repositories.UserAuthRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
        @Autowired
        private UserAuthRepository userRepository;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                UserAuth user = userRepository.findByUsername(username)
                                .orElseThrow(() -> new UsernameNotFoundException("UserAuth not found"));

                return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                                user.getRoles().stream()
                                                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                                                .collect(Collectors.toList()));
        }
}