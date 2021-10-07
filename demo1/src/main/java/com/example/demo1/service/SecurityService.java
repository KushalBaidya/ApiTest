package com.example.demo1.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
