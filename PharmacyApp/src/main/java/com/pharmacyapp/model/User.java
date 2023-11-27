package com.pharmacyapp.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
    private int id;
    private String name;
    private String passwordHash; // This is the hashed password, not the plaintext password.
    private String role;

    // No-arg constructor is required for frameworks and libraries.
    public User() {
    }

    // Constructor with parameters
    public User(int id, String name, String password, String role) {
        this.id = id;
        this.name = name;
        // Directly store the hash of the password
        this.passwordHash = hashPassword(password);
        this.role = role;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return passwordHash;
    }


    public String getRole() {
        return role;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    // The setPassword method should accept a plaintext password and store its hash.
    public void setPassword(String password) {
        this.passwordHash = hashPassword(password);
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Utility method to hash passwords
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

    // Method to check if the user has a certain role
    public boolean hasRole(String role) {
        return this.role.equalsIgnoreCase(role);
    }

    // The toString method should avoid exposing sensitive data like the password hash.
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

}
