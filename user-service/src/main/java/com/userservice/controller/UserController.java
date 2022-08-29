package com.userservice.controller;

import com.userservice.entity.User;
import com.userservice.model.Sale;
import com.userservice.model.Order;
import com.userservice.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        var users = userService.getAll();
        if(users.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(users);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") Long id) {
        var user = userService.getUserById(id);
        if(user== null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(user);
        }
    }

    @PostMapping()
    public ResponseEntity<User> save(@RequestBody User user) {
        return ResponseEntity.ok(userService.save(user));
    }

    @GetMapping("/order/{userId}")
    public ResponseEntity<List<Order>> getOrders(@PathVariable("userId") Long userId) {
        if(userService.getUserById(userId) != null){
            return ResponseEntity.ok(userService.getOrders(userId));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/order/{userId}")
    public ResponseEntity<Order> saveOrder(@PathVariable("userId") Long userId, @RequestBody Order order) {
        if(userService.getUserById(userId) == null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(userService.saveOrder(userId, order));
        }
    }

    @GetMapping("/sale/{userId}")
    public ResponseEntity<List<Sale>> getSales(@PathVariable("userId") Long userId) {
        if(userService.getUserById(userId) == null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(userService.getSales(userId));
        }
    }

    @PostMapping("/sale/{userId}")
    public ResponseEntity<Sale> saveSale(@PathVariable("userId") Long userId, @RequestBody Sale sale) {
        if(userService.getUserById(userId) == null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(userService.saveSale(userId, sale));
        }
    }

    @GetMapping("/getAll/{userId}")
    public ResponseEntity<Map<String, Object>> getAllVehicles(@PathVariable("userId") Long userId) {
        Map<String, Object> result = userService.getUserAndSalesAndOrder(userId);
        return ResponseEntity.ok(result);
    }

    //Método para llamar al fallbackMethod del CB
    @CircuitBreaker(name = "users", fallbackMethod = "fallBackGetMessageFromCB")
    @GetMapping("/cb")
    public ResponseEntity<Map<String, Object>> getMessageFromCB(Long id) {
        id=1L;
        Map<String, Object> result = userService.getUserAndSalesAndOrder(id);
        return ResponseEntity.ok(result);
    }


    private ResponseEntity<Map<String, Object>>  fallBackGetMessageFromCB(Long id, RuntimeException e) {
        return new ResponseEntity("FallbackMethod para test de Circuit Breaker", HttpStatus.OK);
    }

}
