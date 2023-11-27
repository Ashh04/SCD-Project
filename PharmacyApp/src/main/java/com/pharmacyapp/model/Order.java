package com.pharmacyapp.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Order entity representing a customer's order.
 */
public class Order {
    private int id;
    private int customerId; // Assuming there's a separate Customer entity related to User
    private String status; // e.g., "Pending", "Completed", "Cancelled"
    private Timestamp timestamp; // Time when the order was placed
    private List<OrderItem> items; // List of items associated with this order

    // Constructor
    public Order(int customerId) {
        this.customerId = customerId;
        this.status = "Pending";
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.items = new ArrayList<>();
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    // Add an item to the order
    public void addItem(OrderItem item) {
        items.add(item);
    }

    // Remove an item from the order
    public void removeItem(OrderItem item) {
        items.remove(item);
    }

    // Calculate the total cost of the order
    public double calculateTotalCost() {
        return items.stream().mapToDouble(item -> item.getQuantity() * item.getUnitPrice().doubleValue()).sum();
    }

    // Complete the order
    public void completeOrder() {
        setStatus("Completed");
        // Additional logic for completing the order such as inventory reduction can be added here
    }

    // Cancel the order
    public void cancelOrder() {
        if (!"Completed".equals(status)) {
            setStatus("Cancelled");
        } else {
            throw new IllegalStateException("Completed orders cannot be cancelled.");
        }
    }

    // Generate an invoice representation of the order
    public String generateInvoice() {
        StringBuilder invoice = new StringBuilder();
        invoice.append("Order ID: ").append(id).append("\n")
                .append("Timestamp: ").append(timestamp).append("\n")
                .append("Status: ").append(status).append("\n")
                .append("Items:\n");

        for (OrderItem item : items) {
            invoice.append(item.toString()).append("\n");
        }

        invoice.append("Total Cost: ").append(calculateTotalCost());

        return invoice.toString();
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", status='" + status + '\'' +
                ", timestamp=" + timestamp +
                ", items=" + items +
                '}';
    }
}
