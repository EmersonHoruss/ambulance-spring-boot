package com.ambulance.ambulance.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ambulance.ambulance.DTOs.entities.user.UserShowDTO;
import com.ambulance.ambulance.DTOs.responses.ResponseDTO;
import com.ambulance.ambulance.auth.entities.UserAuth;
import com.ambulance.ambulance.services.UserService;
import com.ambulance.ambulance.utils.specification.Specification;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> get(@PathVariable Long id) {
        UserAuth entity = this.userService.get(id);
        return ResponseEntity.ok(new ResponseDTO(entity.asShowDTO()));
    }

    @GetMapping("")
    public ResponseEntity<ResponseDTO> get(
            @RequestParam(required = false) String query,
            @PageableDefault(size = 10) Pageable pageable) {
        Page<UserAuth> page = this.userService.get(new Specification<UserAuth>(query), pageable);
        List<UserShowDTO> dtos = page
                .getContent()
                .stream()
                .map(e -> e.asShowDTO())
                .collect(Collectors.toList());
        return ResponseEntity.ok(new ResponseDTO(dtos, page, query));
    }
}
