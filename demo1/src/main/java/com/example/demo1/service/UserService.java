package com.example.demo1.service;

import com.example.demo1.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
