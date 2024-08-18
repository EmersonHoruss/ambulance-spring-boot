package com.ambulance.ambulance.services;

import com.ambulance.ambulance.entities.*;
import com.ambulance.ambulance.repositories.*;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseServiceImplementation<User>{

    @Override
    @Transactional
    public User create(User user){
        User savedUser = super.create(user);
        return savedUser;
    }

}
