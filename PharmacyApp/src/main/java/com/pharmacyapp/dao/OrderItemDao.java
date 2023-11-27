package com.pharmacyapp.dao;

import com.pharmacyapp.model.OrderItem;

import java.util.List;

public interface OrderItemDao {
    void addOrderItem(OrderItem orderItem);
    void updateOrderItem(OrderItem orderItem);
    List<OrderItem> getOrderItemsByOrderId(int orderId);
    OrderItem getOrderItemById(int orderItemId);
    void deleteOrderItem(int orderItemId);
}
