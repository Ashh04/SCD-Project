package com.pharmacyapp.daoimpl;

import com.pharmacyapp.dao.OrderDao;
import com.pharmacyapp.model.Order;
import com.pharmacyapp.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    @Override
    public Order findById(int id) {
        final String query = "SELECT * FROM orders WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Order order = new Order(rs.getInt("customerId"));
                    order.setId(rs.getInt("id"));
                    order.setStatus(rs.getString("status"));
                    order.setTimestamp(rs.getTimestamp("timestamp"));
                    // Additional logic to retrieve associated OrderItems can be added here
                    return order;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();
        final String query = "SELECT * FROM orders";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Order order = new Order(rs.getInt("customerId"));
                order.setId(rs.getInt("id"));
                order.setStatus(rs.getString("status"));
                order.setTimestamp(rs.getTimestamp("timestamp"));
                // Additional logic to retrieve associated OrderItems can be added here
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public List<Order> findByUserId(int userId) {
        List<Order> orders = new ArrayList<>();
        final String query = "SELECT * FROM orders WHERE customerId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Order order = new Order(userId);
                    order.setId(rs.getInt("id"));
                    order.setStatus(rs.getString("status"));
                    order.setTimestamp(rs.getTimestamp("timestamp"));
                    // Additional logic to retrieve associated OrderItems can be added here
                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public void create(Order order) {
        final String query = "INSERT INTO orders (customerId, timestamp, status) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, order.getCustomerId());
            ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            ps.setString(3, order.getStatus());

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        order.setId(generatedKeys.getInt(1));
                    } else {
                        throw new SQLException("Creating order failed, no ID obtained.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Order order) {
        final String query = "UPDATE orders SET customerId = ?, timestamp = ?, status = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, order.getCustomerId());
            ps.setTimestamp(2, order.getTimestamp());
            ps.setString(3, order.getStatus());
            ps.setInt(4, order.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        final String query = "DELETE FROM orders WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
