package com.pharmacyapp.dao;

import com.pharmacyapp.model.CartItem;

import java.util.List;

public interface CartItemDao {
    List<CartItem> findByCartId(int cartId);
    void addCartItem(CartItem cartItem);
    void updateCartItem(CartItem cartItem);
    void deleteCartItem(int cartId, String productCode);
    void deleteAllCartItems(int cartId);
}
