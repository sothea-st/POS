package com.example.pos.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.pos.components.JavaResponse;
import com.example.pos.constant.JavaValidation;
import com.example.pos.entity.Suplier;
import com.example.pos.service.SuplierService;

import jakarta.validation.Valid;

import java.util.ArrayList;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
 
import org.springframework.web.bind.annotation.PostMapping;
 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import java.util.HashMap;



@RestController
@RequestMapping("/api/suplier")
@Validated
public class SuplierController {
    @Autowired
    private SuplierService service;

    @PostMapping()
    public ResponseEntity<?> addSuplier(@Valid @ModelAttribute Suplier suplier) {
        HashMap<String,String> err = new HashMap<>();
        String key="contact";
        String contact = JavaValidation.checkPhone(suplier.getContact());

        if( !contact.isEmpty() ) {
            err.put(key, contact);
            return ResponseEntity.status(500).body(err);
        }
      
        Suplier s = service.addSuplier(suplier);
        return JavaResponse.success(s);
    }

    @GetMapping()
    public ResponseEntity<?> getSuplier() {
        ArrayList<Suplier> data = service.getSuplier();
        return JavaResponse.success(data);
    }

    
}
