package com.ambulance.ambulance.DTOs.entities.user;

import com.ambulance.ambulance.DTOs.entities.BaseUpdateDTO;
import com.ambulance.ambulance.entities.*;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.*;
import org.springframework.lang.Nullable;
import java.time.LocalDate;

@Getter
@Setter
public class UserUpdateDTO extends BaseUpdateDTO<User>{
    private String name;
    private String email;
    private String password;
    private String roles;

    @Override
    public User asEntity() {
        User user = new User();
        user.setId(this.getId());
        user.setName(this.getName());
        user.setEmail(this.getEmail());
        user.setPassword(this.getPassword());
        user.setRoles(this.getRoles());
        return user;
    }

}
