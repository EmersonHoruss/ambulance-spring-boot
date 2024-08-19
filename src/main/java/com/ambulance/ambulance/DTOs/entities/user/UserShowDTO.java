package com.ambulance.ambulance.DTOs.entities.user;

import java.util.Set;

import com.ambulance.ambulance.DTOs.entities.BaseShowDTO;
import com.ambulance.ambulance.entities.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserShowDTO extends BaseShowDTO<User> {
    private String name;
    private String email;
    private String password;
    private Set<Role> authorities;
}
