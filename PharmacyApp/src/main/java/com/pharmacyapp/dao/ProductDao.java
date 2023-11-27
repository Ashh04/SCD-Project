package com.pharmacyapp.dao;

import com.pharmacyapp.model.Product;

import java.util.List;

public interface ProductDao {
    Product findByCode(String code);
    List<Product> findAll();
    void create(Product product);
    void update(Product product);
    void delete(String code);
}
