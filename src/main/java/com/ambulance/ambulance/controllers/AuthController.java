package com.ambulance.ambulance.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ambulance.ambulance.DTOs.entities.auth.LoginDTO;
import com.ambulance.ambulance.DTOs.entities.user.UserCreateDTO;
import com.ambulance.ambulance.DTOs.entities.user.UserShowDTO;
import com.ambulance.ambulance.auth.DatosJWTToken;
import com.ambulance.ambulance.services.AuthService;

import jakarta.validation.Valid;

@RestController
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<DatosJWTToken> login(@RequestBody @Valid LoginDTO loginDTO) {
        String JWTToken = this.authService.login(loginDTO);
        return ResponseEntity.ok(new DatosJWTToken(JWTToken));
    }

    @PostMapping("/signin")
    public ResponseEntity<UserShowDTO> signin(@RequestBody @Valid UserCreateDTO userCreateDTO) {
        UserShowDTO userShowDTO = this.authService.signin(userCreateDTO);
        return ResponseEntity.ok(userShowDTO);
    }
}
