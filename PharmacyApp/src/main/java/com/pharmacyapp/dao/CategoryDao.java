package com.pharmacyapp.dao;

import com.pharmacyapp.model.Category;

import java.util.List;

public interface CategoryDao {
    Category findById(int id);
    List<Category> findAll();
    List<Category> findSubcategories(int parentId);
    void create(Category category);
    void update(Category category);
    void delete(int id);
}
