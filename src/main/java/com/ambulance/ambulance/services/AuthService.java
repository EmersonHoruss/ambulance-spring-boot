package com.ambulance.ambulance.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ambulance.ambulance.DTOs.entities.auth.LoginDTO;
import com.ambulance.ambulance.DTOs.entities.user.UserCreateDTO;
import com.ambulance.ambulance.DTOs.entities.user.UserShowDTO;
import com.ambulance.ambulance.auth.TokenService;
import com.ambulance.ambulance.entities.User;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    public String login(LoginDTO loginDTO) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                loginDTO.getEmail(),
                loginDTO.getPassword());
        this.authenticationManager.authenticate(authentication);
        Authentication authenticatedUser = this.authenticationManager.authenticate(authentication);
        String JWTToken = this.tokenService.generateToken((User) authenticatedUser.getPrincipal());
        return JWTToken;
    }

    public UserShowDTO signin(UserCreateDTO userCreateDTO) {
        String rawPassword = userCreateDTO.getPassword();
        String encodedPassword = this.passwordEncoder.encode(rawPassword);
        userCreateDTO.setPassword(encodedPassword);

        User user = userCreateDTO.asEntity();
        User savedUser = this.userService.create(user);

        return savedUser.asShowDTO();
    }
}
