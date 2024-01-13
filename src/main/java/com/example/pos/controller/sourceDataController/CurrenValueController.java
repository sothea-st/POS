package com.example.pos.controller.sourceDataController;

import org.apache.catalina.connector.Response;
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
import com.example.pos.entity.sourceData.CurrencyValue;
import com.example.pos.service.sourceDataService.CurrencyValueService;
import java.util.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/currencyValue")
public class CurrenValueController {
    @Autowired
    private CurrencyValueService service;

    @PostMapping
    public ResponseEntity<?> addCurrency(@Valid @RequestBody CurrencyValue c) {
        CurrencyValue data = service.addCurrency(c);
        return JavaResponse.success(data);
    }

    @GetMapping
    public ResponseEntity<?> getAllCurrency(){
        List<CurrencyValue> data = service.getAllCurrencyValue();
        return JavaResponse.success(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCurrencyById(@PathVariable("id") int id){
        CurrencyValue data = service.getCurrencyById(id);
        return JavaResponse.success(data);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCurrency(@PathVariable("id") int id , @RequestBody CurrencyValue c) {
        service.deleteCurrency(id, c);
        return JavaResponse.deleteSuccess(id);        
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCurrency(@PathVariable("id") int id , @RequestBody CurrencyValue c) {
        CurrencyValue data = service.updateCurrency(id, c);
        return JavaResponse.success(data);
    }
    
}
