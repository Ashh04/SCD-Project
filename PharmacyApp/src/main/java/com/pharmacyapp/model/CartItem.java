package com.pharmacyapp.model;

import java.math.BigDecimal;

/**
 * CartItem entity representing a product added to a shopping cart.
 */
public class CartItem {
    private int cartId; // ID for the cart this item is associated with
    private String productCode; // Code identifying the product
    private int quantity; // Quantity of the product added to the cart
    private BigDecimal unitPrice; // Price per unit of the product at the time it was added

    /**
     * Constructor for the CartItem class.
     *
     * @param cartId      The shopping cart ID.
     * @param productCode The product code.
     * @param quantity    The quantity of the product.
     * @param unitPrice   The price per unit of the product.
     */
    public CartItem(int cartId, String productCode, int quantity, BigDecimal unitPrice) {
        this.cartId = cartId;
        this.productCode = productCode;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    // Getters and Setters
    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * Calculates the total price for the quantity of this product in the cart.
     *
     * @return The total price as BigDecimal.
     */
    public BigDecimal getTotalPrice() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "cartId=" + cartId +
                ", productCode='" + productCode + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
