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
import com.example.pos.entity.people.Customer;
import com.example.pos.projections.customerProjection.CustomerProjection;
import com.example.pos.service.sourceDataService.CustomerService;

import jakarta.validation.Valid;
import java.util.*;
@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    private CustomerService service;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Customer c) {
        Customer data = service.add(c);
        return JavaResponse.success(data);
    }

    @GetMapping
    public ResponseEntity<?> read() {
        List<CustomerProjection> data = service.read();
        return JavaResponse.success(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id) {
        Customer data = service.readById(id);
        return JavaResponse.success(data);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id , @RequestBody Customer c) {
         service.deleteById(id,c);
        return JavaResponse.deleteSuccess(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id , @RequestBody Customer c) {
        Customer data =  service.update(id,c);
        return JavaResponse.success(data);
    }

    @GetMapping("/getCustomerId")
    public ResponseEntity<?> getCusotmerId() {
        String data = service.getCustomerId();
        return JavaResponse.success(data);
    }


}
