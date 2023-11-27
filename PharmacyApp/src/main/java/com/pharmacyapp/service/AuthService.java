package com.pharmacyapp.service;

import com.pharmacyapp.model.User;

public interface AuthService {
    User login(String username, String password);
    boolean logout(User user);
    boolean changePassword(User user, String oldPassword, String newPassword);
}
