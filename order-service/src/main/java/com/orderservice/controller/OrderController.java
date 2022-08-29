package com.orderservice.controller;

import com.orderservice.entity.Order;
import com.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> getAll() {
        var orders = orderService.getAll();
        if(orders.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(orders);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getById(@PathVariable("id") Long id) {
        var order = orderService.getOrderById(id);
        if(order == null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(order);
        }
    }

    @PostMapping
    public ResponseEntity<Order> save(@RequestBody Order order) {
        return ResponseEntity.ok(orderService.save(order));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getByUserId(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(orderService.byUserId(userId));
    }

}
