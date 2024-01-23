package com.example.pos.controller.sourceDataController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pos.components.JavaResponse;
import com.example.pos.entity.sourceData.Brand;
import com.example.pos.repository.sourceDataRepository.BrandRepository;
import com.example.pos.service.sourceDataService.BrandService;
import java.util.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/brand")
public class BrandController {
    @Autowired
    private BrandService service;

    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody Brand b) {
        Brand data = service.add(b);
        return JavaResponse.success(data);
    }

    @GetMapping
    public ResponseEntity<?> read(){
        List<Brand> data = service.read();
        return JavaResponse.success(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBrandById(@PathVariable("id") int id) {
        Brand data = service.getBrandById(id);
        return JavaResponse.success(data);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBrand(@PathVariable("id") int id , @RequestBody Brand b) {
        service.deleteBrand(id, b);
        return JavaResponse.deleteSuccess(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody Brand b) {
        Brand data = service.updateBrand(id, b);
        return JavaResponse.success(data);
    }

}
