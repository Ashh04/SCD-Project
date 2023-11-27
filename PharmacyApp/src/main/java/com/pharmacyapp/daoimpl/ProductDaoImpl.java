package com.pharmacyapp.daoimpl;

import com.pharmacyapp.dao.ProductDao;
import com.pharmacyapp.model.Product;
import com.pharmacyapp.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    @Override
    public Product findByCode(String code) {
        final String query = "SELECT * FROM products WHERE code = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, code);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRowToProduct(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        final String query = "SELECT * FROM products";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                products.add(mapRowToProduct(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }


    @Override
    public void create(Product product) {
        final String query = "INSERT INTO products (code, name, description, stockQuantity, price, categoryId) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, product.getCode());
            ps.setString(2, product.getName());
            ps.setString(3, product.getDescription());
            ps.setInt(4, product.getStockQuantity());
            ps.setBigDecimal(5, product.getPrice());

            // Here we check if getCategory() returns a non-null Integer
            Integer categoryId = product.getCategoryId();
            if (categoryId != null) {
                ps.setInt(6, categoryId);
            } else {
                ps.setNull(6, Types.INTEGER);
            }

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void update(Product product) {
        final String query = "UPDATE products SET name = ?, description = ?, stockQuantity = ?, price = ?, categoryId = ? WHERE code = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setInt(3, product.getStockQuantity());
            ps.setBigDecimal(4, product.getPrice());

            // Here we check if getCategoryId() returns a non-null Integer
            Integer categoryId = product.getCategoryId();
            if (categoryId != null) {
                ps.setInt(5, categoryId);
            } else {
                ps.setNull(5, Types.INTEGER);
            }

            ps.setString(6, product.getCode());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String code) {
        final String query = "DELETE FROM products WHERE code = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, code);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Product mapRowToProduct(ResultSet rs) throws SQLException {
        return new Product(
                rs.getString("code"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getInt("stockQuantity"),
                rs.getBigDecimal("price"),
                rs.getInt("categoryId")
        );
    }
}
