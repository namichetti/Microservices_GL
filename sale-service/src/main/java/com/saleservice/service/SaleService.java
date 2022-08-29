package com.saleservice.service;

import com.saleservice.entity.Sale;

import java.util.List;

public interface SaleService {

     List<Sale> getAll();
     Sale getSaleById(Long id);
     Sale save(Sale sale);
     List<Sale> byUserId(Long userId);
}
