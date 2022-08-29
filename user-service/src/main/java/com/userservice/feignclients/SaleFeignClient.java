package com.userservice.feignclients;

import com.userservice.model.Sale;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "sale-service", path = "/sale")
public interface SaleFeignClient {

    @PostMapping
    Sale save(@RequestBody Sale sale);

    @GetMapping("/user/{userId}")
    List<Sale> getByUserId(@PathVariable("userId") Long userId);
}
