package com.pharmacyapp.controller;

import com.pharmacyapp.model.User;
import com.pharmacyapp.service.AuthService;
import com.pharmacyapp.service.AuthServiceImpl;

public class LoginController {

    private AuthService authService;

    public LoginController() {
        this.authService = new AuthServiceImpl(); // Ideally injected
    }

    public User processLogin(String username, String password) {
        User user = authService.login(username, password);
        if (user != null) {
            // Login successful
            return user;
        } else {
            // Login failed
            return null;
        }
    }
}
