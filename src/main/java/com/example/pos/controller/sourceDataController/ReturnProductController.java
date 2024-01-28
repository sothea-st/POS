package com.example.pos.controller.sourceDataController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pos.components.JavaResponse;
import com.example.pos.entity.sourceData.ReturnProduct;
import com.example.pos.repository.productProjection.ProductProjection;
import com.example.pos.service.sourceDataService.ReturnProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/returnProduct")
public class ReturnProductController {
    @Autowired
    private ReturnProductService service;

    @PostMapping
    public ResponseEntity<?> returnProduct(@Valid @RequestBody ReturnProduct r) {
        service.returnProduct(r);
        return JavaResponse.success("return product success");
    }

    @GetMapping("/{barcode}")
    public ResponseEntity<?> getProductByBarcode(@PathVariable("barcode") String barcode) {
        ProductProjection data = service.searchProdcutByBarcode(barcode);
        return JavaResponse.success(data);
    }

}
