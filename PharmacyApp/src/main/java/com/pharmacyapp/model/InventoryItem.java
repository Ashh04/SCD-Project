package com.pharmacyapp.model;

import java.util.Date;

public class InventoryItem {

    private int id;
    private int productId;
    private int quantity;
    private Date expirationDate;

    // Default constructor
    public InventoryItem() {
    }

    // Constructor with all fields
    public InventoryItem(int id, int productId, int quantity, Date expirationDate) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    // Override toString() method for debugging purposes
    @Override
    public String toString() {
        return "InventoryItem{" +
                "id=" + id +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
