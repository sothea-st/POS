package com.example.pos.entity.sourceData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pos.components.JavaResponse;
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
}
