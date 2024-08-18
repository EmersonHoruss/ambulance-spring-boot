package com.ambulance.ambulance.entities;

import com.ambulance.ambulance.DTOs.entities.user.UserShowDTO;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User extends BaseEntity {
    @Column(length = 255)
    private String name;
    @Column(length = 255)
    private String email;
    @Column(length = 255)
    private String password;
    @Column(length = 255)
    private String roles;

    @Override
    public UserShowDTO asShowDTO(){
        UserShowDTO userShowDTO = new UserShowDTO();
        userShowDTO.setId(this.getId());
        userShowDTO.setActivated(this.getActivated());
        userShowDTO.setName(this.getName());
        userShowDTO.setEmail(this.getEmail());
        userShowDTO.setPassword(this.getPassword());
        userShowDTO.setRoles(this.getRoles());
        return userShowDTO;
    }
}
