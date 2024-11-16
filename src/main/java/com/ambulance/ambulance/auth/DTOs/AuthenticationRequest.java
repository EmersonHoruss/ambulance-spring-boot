package com.ambulance.ambulance.auth.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationRequest {
    private String email;
    private String password;
}
