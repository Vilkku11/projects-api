package com.projects.projects_api.controller;

import com.projects.projects_api.dto.UserDto;
import com.projects.projects_api.model.MyUser;
import com.projects.projects_api.repository.UserRepository;
import com.projects.projects_api.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder, UserService userService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;

    }



    @PostMapping("/register")
    public MyUser createUser(@RequestBody MyUser user){
        return userService.registerUser(user);

    }

    @PostMapping("/login")
    public String authenticateAndGetToken(@RequestBody UserDto userDto) {
        return userService.loginUser(userDto);
    }

}
