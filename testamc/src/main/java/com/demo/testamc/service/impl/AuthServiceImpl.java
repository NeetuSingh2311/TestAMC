package com.demo.testamc.service.impl;

import com.demo.testamc.dto.LoginRequest;
import com.demo.testamc.dto.LoginResponse;
import com.demo.testamc.dto.SignupRequest;
import com.demo.testamc.enums.UserType;
import com.demo.testamc.models.User;
import com.demo.testamc.repository.UserRepository;
import com.demo.testamc.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Optional<User> userOptional = userRepository.findByEmail(loginRequest.getEmail());
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            boolean match = passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());
            if(match) {
               return LoginResponse.builder()
                        .name(user.getFullName())
                        .role(user.getRole())
                        .email(user.getEmail())
                        .build();
            }
        }
        return new LoginResponse();
    }

    @Override
    public User signup(SignupRequest signupRequest) {
        User user = User.builder()
                .email(signupRequest.getEmail())
                .password(passwordEncoder.encode(signupRequest.getPassword()))
                .role(UserType.USER.toString())
                .contactNumber(signupRequest.getContactNumber())
                .fullName(signupRequest.getFullName())
                .pan(signupRequest.getPan())
                .address(signupRequest.getAddress())
                .build();

        return userRepository.save(user);
    }
}