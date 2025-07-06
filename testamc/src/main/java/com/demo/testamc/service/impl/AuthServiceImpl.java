package com.demo.testamc.service.impl;

import com.demo.testamc.dto.LoginRequest;
import com.demo.testamc.models.User;
import com.demo.testamc.repository.UserRepository;
import com.demo.testamc.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;

    @Autowired
    AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean login(LoginRequest loginRequest) {
        Optional<User> userOptional = userRepository.findByEmail(loginRequest.getEmail());
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            System.out.println(user);
            if(user.getPasswordHash().equals(loginRequest.getPassword())) {
                return true;
            }
        };
        return false;
    }
}
