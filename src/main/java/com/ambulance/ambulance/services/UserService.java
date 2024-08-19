package com.ambulance.ambulance.services;

import com.ambulance.ambulance.entities.*;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseServiceImplementation<User> {

    @Override
    @Transactional
    public User create(User user) {
        User savedUser = super.create(user);
        return savedUser;
    }

}
