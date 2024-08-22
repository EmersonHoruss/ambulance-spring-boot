package com.ambulance.ambulance.repositories;

import com.ambulance.ambulance.entities.User;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User> {
    UserDetails findByEmail(String email);
}
