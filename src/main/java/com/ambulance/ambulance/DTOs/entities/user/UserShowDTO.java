package com.ambulance.ambulance.DTOs.entities.user;

import java.util.Set;

import com.ambulance.ambulance.DTOs.entities.BaseShowDTO;
import com.ambulance.ambulance.auth.entities.Role;
import com.ambulance.ambulance.auth.entities.UserAuth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserShowDTO extends BaseShowDTO<UserAuth> {
    private String name;
    private Set<Role> roles;
}
