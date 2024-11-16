package com.ambulance.ambulance.auth.entities;

import java.util.HashSet;
import java.util.Set;

import com.ambulance.ambulance.DTOs.entities.user.UserShowDTO;
import com.ambulance.ambulance.entities.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user-auth")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserAuth extends BaseEntity<UserShowDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    public UserShowDTO asShowDTO() {
        UserShowDTO dto = new UserShowDTO();
        dto.setId(this.id);
        dto.setName(this.username);
        dto.setRoles(this.roles);

        return dto;
    }
}
