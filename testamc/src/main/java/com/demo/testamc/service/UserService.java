package com.demo.testamc.service;

import com.demo.testamc.models.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    User getUserByEmail(String email);
}

    