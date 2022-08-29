package com.saleservice.controller;

import com.saleservice.entity.Sale;
import com.saleservice.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    SaleService saleService;

    @GetMapping
    public ResponseEntity<List<Sale>> getAll() {
        var sales = saleService.getAll();
        if(sales.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(sales);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sale> getById(@PathVariable("id") Long id) {
        var sale = saleService.getSaleById(id);
        if(sale == null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(sale);
        }
    }

    @PostMapping
    public ResponseEntity<Sale> save(@RequestBody Sale sale) {
        return ResponseEntity.ok(saleService.save(sale));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Sale>> getByUserId(@PathVariable("userId") Long userId) throws InterruptedException {
        return ResponseEntity.ok(saleService.byUserId(userId));
    }

}
