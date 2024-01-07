package com.example.pos.controller.sourceDataController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pos.components.JavaResponse;
import com.example.pos.entity.sourceData.CustomerType;
import com.example.pos.service.sourceDataService.CustomerTypeService;

import jakarta.validation.Valid;
import java.util.*;
@RestController
@RequestMapping("/api/customerType")
public class CustomerTypeController {
     @Autowired
     private CustomerTypeService service;

     @PostMapping
     public ResponseEntity<?> add(@Valid @ModelAttribute CustomerType c) {
          CustomerType data = service.add(c);
          return JavaResponse.success(data);
     }

     @GetMapping
     public ResponseEntity<?> read() {
          List<CustomerType> data = service.read();
          return JavaResponse.success(data);
     }

     @GetMapping("/{id}")
     public ResponseEntity<?> readById(@PathVariable("id") int id) {
          CustomerType data = service.readById(id);
          return JavaResponse.success(data);
     }

     @DeleteMapping("/{id}")
     public ResponseEntity<?> delete(@PathVariable("id") int id,@RequestBody CustomerType c) {
          service.delete(id,c);
          return JavaResponse.deleteSuccess(id);
     }

     @PutMapping("/{id}")
     public ResponseEntity<?> update(@PathVariable("id") int id,@RequestBody CustomerType c) {
          CustomerType data = service.update(id,c);
          return JavaResponse.success(data);
     }



}
