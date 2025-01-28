package com.projects.projects_api.service;

import com.projects.projects_api.dto.UserDto;
import com.projects.projects_api.exception.UserException;
import com.projects.projects_api.model.MyUser;
import com.projects.projects_api.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService{


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailService userDetailService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService, UserDetailService userDetailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userDetailService = userDetailService;
    }


    public MyUser registerUser(MyUser user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UserException.UserAlreadyExists(user.getUsername());
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

   public String loginUser(UserDto userDto) {

       Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
               userDto.username(), userDto.password()
       ));
       if (authentication.isAuthenticated()){
            return jwtService.generateToken(userDetailService.loadUserByUsername(userDto.username()));
       } else {
           throw new UsernameNotFoundException("Invalid credentials");
       }
   }

}
