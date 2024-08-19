package com.ambulance.ambulance.controllers;

import com.ambulance.ambulance.DTOs.entities.user.UserActivatedDTO;
import com.ambulance.ambulance.DTOs.entities.user.UserCreateDTO;
import com.ambulance.ambulance.DTOs.entities.user.UserShowDTO;
import com.ambulance.ambulance.DTOs.entities.user.UserUpdateDTO;
import com.ambulance.ambulance.entities.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/users")
public class UserController
                extends
                BaseControllerImplementation<User, UserCreateDTO, UserShowDTO, UserUpdateDTO, UserActivatedDTO> {
}
