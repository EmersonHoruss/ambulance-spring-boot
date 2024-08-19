package com.ambulance.ambulance.DTOs.entities.user;

import java.util.Set;

import com.ambulance.ambulance.DTOs.entities.BaseUpdateDTO;
import com.ambulance.ambulance.entities.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateDTO extends BaseUpdateDTO<User> {
    private String name;
    private String email;
    private String password;
    private Set<Role> authorities;

    @Override
    public User asEntity() {
        User user = new User();
        user.setId(this.getId());
        user.setName(this.getName());
        user.setEmail(this.getEmail());
        user.setPassword(this.getPassword());
        user.setAuthorities(this.getAuthorities());
        return user;
    }

}
