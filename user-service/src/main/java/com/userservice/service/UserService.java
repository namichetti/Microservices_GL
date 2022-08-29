package com.userservice.service;

import com.userservice.entity.User;
import com.userservice.model.Order;
import com.userservice.model.Sale;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<User> getAll();
    User getUserById(Long id);
    User save(User user);
    List<Order> getOrders(Long userId);
    List<Sale> getSales(Long userId);
    Order saveOrder(Long userId, Order order);
    Sale saveSale(Long userId, Sale sale);
    Map<String, Object> getUserAndSalesAndOrder(Long userId);
}
