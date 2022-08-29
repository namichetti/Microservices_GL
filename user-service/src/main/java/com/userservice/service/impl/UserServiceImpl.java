package com.userservice.service.impl;

import com.userservice.entity.User;
import com.userservice.feignclients.SaleFeignClient;
import com.userservice.feignclients.OrderFeignClient;
import com.userservice.model.Sale;
import com.userservice.model.Order;
import com.userservice.repository.UserRepository;
import com.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderFeignClient orderFeignClient;

    @Autowired
    SaleFeignClient saleFeignClient;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public List<Order> getOrders(Long userId) {
        return this.orderFeignClient.getByUserId(userId);
    }

    public List<Sale> getSales(Long userId) {
        return this.saleFeignClient.getByUserId(userId);
    }

    public Order saveOrder(Long userId, Order order) {
        order.setUserId(userId);
        return orderFeignClient.save(order);
    }

    public Sale saveSale(Long userId, Sale sale) {
        sale.setUserId(userId);
        return saleFeignClient.save(sale);
    }

    public Map<String, Object> getUserAndSalesAndOrder(Long userId) {
        Map<String, Object> response = new HashMap<>();
        var user = this.getUserById(userId);

        if(this.getUserById(userId) == null) {
            response.put("Message", "User doesn't exist");
            return response;
        }

        response.put("User", user);
        var orders = orderFeignClient.getByUserId(userId);

        if(orders.isEmpty()){
            response.put("Orders", "This user don't have orders");
        }else{
            response.put("Orders", orders);
        }

        var sales = saleFeignClient.getByUserId(userId);

        if(sales.isEmpty())
            response.put("Sales", "This user don't have sales");
        else
            response.put("Sales", sales);
        return response;
    }
}
