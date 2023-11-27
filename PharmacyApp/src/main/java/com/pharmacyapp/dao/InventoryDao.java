package com.pharmacyapp.dao;

import com.pharmacyapp.model.InventoryItem;

import java.util.List;

public interface InventoryDao {
    InventoryItem findById(int id);
    List<InventoryItem> findAll();
    void save(InventoryItem item);
    void update(InventoryItem item);
    void delete(int id);
    List<InventoryItem> findLowStockItems(int threshold);
    void replenishStock(int id, int newQuantity);
}
