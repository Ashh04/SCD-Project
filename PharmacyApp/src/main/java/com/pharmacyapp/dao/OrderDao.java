package com.pharmacyapp.dao;

import com.pharmacyapp.model.Order;

import java.util.List;

public interface OrderDao {
    Order findById(int id);
    List<Order> findAll();
    List<Order> findByUserId(int userId);
    void create(Order order);
    void update(Order order);
    void delete(int id);
}
