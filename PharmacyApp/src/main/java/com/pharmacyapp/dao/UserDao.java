package com.pharmacyapp.dao;

import com.pharmacyapp.model.User;

import java.util.List;

public interface UserDao {
    User findById(int id);
    List<User> findAll();
    void create(User user);
    void update(User user);
    void delete(int id);
}

