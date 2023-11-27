package com.pharmacyapp.daoimpl;

import com.pharmacyapp.dao.InventoryDao;
import com.pharmacyapp.model.InventoryItem;
import com.pharmacyapp.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryDaoImpl implements InventoryDao {

    private Connection connection;

    public InventoryDaoImpl() throws SQLException {
        connection = DatabaseConnection.getConnection();
    }

    @Override
    public InventoryItem findById(int id) {
        InventoryItem item = null;
        try {
            String query = "SELECT * FROM inventory WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                item = new InventoryItem();
                item.setId(rs.getInt("id"));
                item.setProductId(rs.getInt("product_id"));
                item.setQuantity(rs.getInt("quantity"));
                item.setExpirationDate(rs.getDate("expiration_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public List<InventoryItem> findAll() {
        List<InventoryItem> items = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM inventory");
            while (rs.next()) {
                InventoryItem item = new InventoryItem();
                item.setId(rs.getInt("id"));
                item.setProductId(rs.getInt("product_id"));
                item.setQuantity(rs.getInt("quantity"));
                item.setExpirationDate(rs.getDate("expiration_date"));
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public void save(InventoryItem item) {
        try {
            String query = "INSERT INTO inventory (product_id, quantity, expiration_date) VALUES (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, item.getProductId());
            ps.setInt(2, item.getQuantity());
            ps.setDate(3, new Date(item.getExpirationDate().getTime()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(InventoryItem item) {
        try {
            String query = "UPDATE inventory SET product_id = ?, quantity = ?, expiration_date = ? WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, item.getProductId());
            ps.setInt(2, item.getQuantity());
            ps.setDate(3, new Date(item.getExpirationDate().getTime()));
            ps.setInt(4, item.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            String query = "DELETE FROM inventory WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<InventoryItem> findLowStockItems(int threshold) {
        List<InventoryItem> lowStockItems = new ArrayList<>();
        try {
            String query = "SELECT * FROM inventory WHERE quantity <= ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, threshold);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                InventoryItem item = new InventoryItem();
                item.setId(rs.getInt("id"));
                item.setProductId(rs.getInt("product_id"));
                item.setQuantity(rs.getInt("quantity"));
                item.setExpirationDate(rs.getDate("expiration_date"));
                lowStockItems.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lowStockItems;
    }

    @Override
    public void replenishStock(int id, int newQuantity) {
        try {
            String query = "UPDATE inventory SET quantity = quantity + ? WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, newQuantity);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
