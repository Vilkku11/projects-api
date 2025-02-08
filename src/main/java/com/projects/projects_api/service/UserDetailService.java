package com.projects.projects_api.service;

import com.projects.projects_api.exception.UserException;
import com.projects.projects_api.model.MyUser;
import com.projects.projects_api.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {


    private final UserRepository userRepository;
    public UserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UserException.InvalidUsernameOrPassword {
        Optional<MyUser> user = userRepository.findByUsername(username);
        if (user.isPresent()){
            var userObj = user.get();
            return User.builder()
                    .username(userObj.getUsername())
                    .password(userObj.getPassword())
                    .build();
        }else {
            throw new UserException.InvalidUsernameOrPassword();
        }
    }

}