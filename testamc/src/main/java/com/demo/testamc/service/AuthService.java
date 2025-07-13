package com.demo.testamc.service;

import com.demo.testamc.dto.LoginRequest;
import com.demo.testamc.dto.LoginResponse;
import com.demo.testamc.dto.SignupRequest;
import com.demo.testamc.models.User;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);
    User signup(SignupRequest signupRequest);
}
