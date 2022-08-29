package com.saleservice.service.impl;

import com.saleservice.entity.Sale;
import com.saleservice.repository.SaleRepository;
import com.saleservice.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    SaleRepository saleRepository;

    public List<Sale> getAll() {
        return saleRepository.findAll();
    }

    public Sale getSaleById(Long id) {
        return saleRepository.findById(id).orElse(null);
    }

    public Sale save(Sale sale) {
        return saleRepository.save(sale);
    }

    public List<Sale> byUserId(Long userId) {
        return saleRepository.findByUserId(userId);
    }
}
