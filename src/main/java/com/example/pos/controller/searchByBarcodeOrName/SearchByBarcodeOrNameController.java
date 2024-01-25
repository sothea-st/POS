package com.example.pos.controller.searchByBarcodeOrName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.pos.components.JavaResponse;
import com.example.pos.entity.Product;
import com.example.pos.service.searchByBarcodeOrNameService.SearchByBarcodeOrNameService;
import java.util.*;
@RestController
@RequestMapping("/api/searchProductByBarcodeOrName")
public class SearchByBarcodeOrNameController {
    @Autowired
    private SearchByBarcodeOrNameService service;

    @GetMapping
    public ResponseEntity<?> search(@RequestParam("code") String code,@RequestParam("valueSearch") String valueSearch) {
        List<Product> data = service.search(code,valueSearch);
        return JavaResponse.success(data);
    }
}
