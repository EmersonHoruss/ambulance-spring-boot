package com.ambulance.ambulance.entities;

import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;

import com.ambulance.ambulance.DTOs.entities.user.UserShowDTO;
import jakarta.persistence.*;
import lombok.*;

@SuppressWarnings("rawtypes")
@Entity
@Table(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User extends BaseEntity implements UserDetails {
    @Column(length = 255)
    private String name;
    @Column(length = 255)
    private String email;
    @Column(length = 255)
    private String password;

    private Set<Role> authorities;

    @Override
    public UserShowDTO asShowDTO() {
        UserShowDTO userShowDTO = new UserShowDTO();
        userShowDTO.setId(this.getId());
        userShowDTO.setActivated(this.getActivated());
        userShowDTO.setName(this.getName());
        userShowDTO.setEmail(this.getEmail());
        userShowDTO.setPassword(this.getPassword());
        userShowDTO.setAuthorities(this.getAuthorities());
        return userShowDTO;
    }

    private boolean accountNonExpired;
    private boolean isEnabled;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;

    @Override
    public String getUsername() {
        return this.email;
    }

}
