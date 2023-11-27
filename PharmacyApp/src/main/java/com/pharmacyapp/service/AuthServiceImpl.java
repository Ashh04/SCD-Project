package com.pharmacyapp.service;

import com.pharmacyapp.dao.UserDao;
import com.pharmacyapp.daoimpl.UserDaoImpl;
import com.pharmacyapp.model.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class AuthServiceImpl implements AuthService {

    private UserDao userDao;

    public AuthServiceImpl() {
        this.userDao = new UserDaoImpl();  // In real scenarios, use Dependency Injection
    }

    @Override
    public User login(String username, String password) {
        // This would involve fetching a user by username and then comparing the hashed password
        List<User> users = userDao.findAll();
        for (User user : users) {
            if (user.getName().equalsIgnoreCase(username) && user.getPassword().equals(hashPassword(password))) {
                return user;  // User is authenticated
            }
        }
        return null;  // User not found or password does not match
    }

    @Override
    public boolean logout(User user) {
        // Depending on session management, invalidate session or token
        // Placeholder implementation as session handling is beyond DAO and Service layers
        return true;
    }

    @Override
    public boolean changePassword(User user, String oldPassword, String newPassword) {
        if (user.getPassword().equals(hashPassword(oldPassword))) {
            user.setPassword(newPassword);  // setPassword will hash the new password
            userDao.update(user);
            return true;
        }
        return false;
    }

    // Utility method for hashing passwords, duplicated from User for simplicity
    private String hashPassword(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to hash password", e);
        }
    }
}
