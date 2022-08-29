package com.userservice.feignclients;

import com.userservice.model.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "order-service",  path = "/order")
public interface OrderFeignClient {

    @PostMapping
    Order save(@RequestBody Order order);

    @GetMapping("/user/{userId}")
    List<Order> getByUserId(@PathVariable("userId") Long userId);
}
