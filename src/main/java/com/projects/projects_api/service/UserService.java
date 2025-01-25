package com.projects.projects_api.service;

import com.projects.projects_api.exception.UserException;
import com.projects.projects_api.model.MyUser;
import com.projects.projects_api.repository.UserRepository;
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

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public MyUser registerUser(MyUser user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UserException.UserAlreadyExists(user.getUsername());
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

   /* @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MyUser> user = userRepository.findByUsername(username);
        if (user.isPresent()){
            var userObj = user.get();
            return User.builder()
                    .username(userObj.getUsername())
                    .password(userObj.getPassword())
                    .build();
        }else {
            throw new UsernameNotFoundException(username);
        }
    }*/

}
