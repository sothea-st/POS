package com.example.pos.controller;

import com.example.pos.components.JavaResponse;
import com.example.pos.entity.Sale;
import com.example.pos.service.SaleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sale")
@Validated
public class SaleController {
    @Autowired
    private SaleService service;

    @PostMapping
    public ResponseEntity<?> saleProduct(@Valid @RequestBody Sale s) {
       var data = service.saleProduct(s);
        return JavaResponse.success(data);
    }

}
