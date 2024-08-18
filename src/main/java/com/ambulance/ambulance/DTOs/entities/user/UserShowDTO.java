package com.ambulance.ambulance.DTOs.entities.user;

import com.ambulance.ambulance.DTOs.entities.BaseShowDTO;
import com.ambulance.ambulance.entities.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class UserShowDTO extends BaseShowDTO<User>{
    private String name;
    private String email;
    private String password;
    private String roles;
}
