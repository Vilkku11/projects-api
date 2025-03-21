package com.projects.projects_api.service;

import com.projects.projects_api.dto.UserDto;
import com.projects.projects_api.exception.UserException;
import com.projects.projects_api.model.MyUser;
import com.projects.projects_api.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final ObjectValidator objectValidator;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService, UserDetailService userDetailService, ObjectValidator objectValidator) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userDetailService = userDetailService;
        this.objectValidator = objectValidator;
    }


    public String registerUser(UserDto userDto) {

        validate(userDto);

        if (userDto.password().length() < 5) {
            throw new UserException.PasswordTooShort();
        }

        if (userRepository.findByUsername(userDto.username()).isPresent()) {
            throw new UserException.UserAlreadyExists(userDto.username());
        }
        MyUser newUser = new MyUser();
        newUser.setUsername(userDto.username());
        newUser.setPassword(passwordEncoder.encode(userDto.password()));
        MyUser user = userRepository.save(newUser);

        if (user.getId() == null) {
            throw new UserException.UserSaveFail();
        }

        return authenticate(userDto);
    }

   public String loginUser(UserDto userDto) {

        validate(userDto);
        return authenticate(userDto);

   }

    public MyUser getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        return userRepository.findByUsername(username)
                .orElseThrow(UserException.InvalidUsernameOrPassword::new);
    }

   private void validate(UserDto userDto) {
        var violations = objectValidator.validate(userDto);
       if(!violations.isEmpty()) {
           throw new UserException.InvalidUsernameOrPassword();
       }
   }

   private String authenticate(UserDto userDto) {
       Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
               userDto.username(), userDto.password()
       ));
       if (authentication.isAuthenticated()){
           return jwtService.generateToken(userDetailService.loadUserByUsername(userDto.username()));
       } else {
           throw new UserException.InvalidUsernameOrPassword();
       }
   }

}
