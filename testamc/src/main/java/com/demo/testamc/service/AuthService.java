package com.demo.testamc.service;

import com.demo.testamc.dto.LoginRequest;

public interface AuthService {
    public boolean login(LoginRequest loginRequest);
}
