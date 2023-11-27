package com.pharmacyapp.daoimpl;

import com.pharmacyapp.dao.CartItemDao;
import com.pharmacyapp.model.CartItem;
import com.pharmacyapp.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartItemDaoImpl implements CartItemDao {
    @Override
    public List<CartItem> findByCartId(int cartId) {
        final String query = "SELECT * FROM cart_items WHERE cart_id = ?";
        List<CartItem> cartItems = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, cartId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cartItems.add(new CartItem(
                        rs.getInt("cart_id"),
                        rs.getString("product_code"),
                        rs.getInt("quantity"),
                        rs.getBigDecimal("unit_price") // Retrieve unit_price from the database
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartItems;
    }

    @Override
    public void addCartItem(CartItem cartItem) {
        final String query = "INSERT INTO cart_items (cart_id, product_code, quantity, unit_price) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, cartItem.getCartId());
            ps.setString(2, cartItem.getProductCode());
            ps.setInt(3, cartItem.getQuantity());
            ps.setBigDecimal(4, cartItem.getUnitPrice()); // Set unit_price in the query
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void updateCartItem(CartItem cartItem) {
        final String query = "UPDATE cart_items SET quantity = ? WHERE cart_id = ? AND product_code = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, cartItem.getQuantity());
            ps.setInt(2, cartItem.getCartId());
            ps.setString(3, cartItem.getProductCode());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCartItem(int cartId, String productCode) {
        final String query = "DELETE FROM cart_items WHERE cart_id = ? AND product_code = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, cartId);
            ps.setString(2, productCode);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAllCartItems(int cartId) {
        final String query = "DELETE FROM cart_items WHERE cart_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, cartId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
