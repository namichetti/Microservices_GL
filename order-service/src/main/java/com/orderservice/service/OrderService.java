package com.orderservice.service;

import com.orderservice.entity.Order;

import java.util.List;

public interface OrderService {

    List<Order> getAll();
    Order getOrderById(Long id);
    Order save(Order order);
    List<Order> byUserId(Long userId);
}
