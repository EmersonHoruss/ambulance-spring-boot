package com.ambulance.ambulance.DTOs.entities.user;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.ambulance.ambulance.DTOs.entities.BaseCreateDTO;
import com.ambulance.ambulance.entities.*;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateDTO extends BaseCreateDTO<User> {
    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    private Set<Role> authorities;

    @Override
    public User asEntity() {
        User user = new User();
        user.setName(this.getName());
        user.setEmail(this.getEmail());
        user.setPassword(this.getPassword());
        user.setAuthorities(this.getAuthorities());
        if (this.authorities == null || this.authorities.isEmpty()) {
            this.authorities = new HashSet<>(Collections.singleton(Role.ROLE_USER));
        }
        return user;
    }

}
