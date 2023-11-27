package com.pharmacyapp.model;

import java.math.BigDecimal;

/**
 * Product entity representing a product in the pharmacy system.
 */
public class Product {
    private String code; // Unique identifier for the product, like a SKU
    private String name;
    private String description;
    private int stockQuantity; // The current quantity in stock
    private BigDecimal price; // The price of the product
    private int categoryId; // The ID of the category this product belongs to

    /**
     * Constructor for creating a new Product instance.
     *
     * @param code          The unique code of the product.
     * @param name          The name of the product.
     * @param description   The description of the product.
     * @param stockQuantity The quantity of the product in stock.
     * @param price         The price of the product.
     * @param categoryId    The category ID of the product.
     */
    public Product(String code, String name, String description, int stockQuantity, BigDecimal price, int categoryId) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.stockQuantity = stockQuantity;
        this.price = price;
        this.categoryId = categoryId;
    }

    // Getters and Setters

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    // Business Methods

    /**
     * Updates the stock quantity of the product, usually after a sale.
     *
     * @param quantitySold The amount of product that was sold.
     */
    public void updateStockAfterSale(int quantitySold) {
        if (quantitySold < 0 || quantitySold > this.stockQuantity) {
            throw new IllegalArgumentException("Invalid quantity sold provided.");
        }
        this.stockQuantity -= quantitySold;
    }

    /**
     * Replenishes the stock for the product.
     *
     * @param quantityAdded The amount of product added to the stock.
     */
    public void replenishStock(int quantityAdded) {
        if (quantityAdded < 0) {
            throw new IllegalArgumentException("Invalid quantity added provided.");
        }
        this.stockQuantity += quantityAdded;
    }

    /**
     * Calculates the total value of the stock for this product.
     *
     * @return The total stock value as BigDecimal.
     */
    public BigDecimal calculateStockValue() {
        return this.price.multiply(BigDecimal.valueOf(this.stockQuantity));
    }

    @Override
    public String toString() {
        return "Product{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", stockQuantity=" + stockQuantity +
                ", price=" + price +
                ", categoryId=" + categoryId +
                '}';
    }
}
