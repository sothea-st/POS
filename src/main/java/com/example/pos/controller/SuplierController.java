package com.example.pos.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.pos.components.JavaResponse;
import com.example.pos.entity.Suplier;
import com.example.pos.service.SuplierService;

import jakarta.validation.Valid;

import java.util.ArrayList;

import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;




@RestController
@RequestMapping("/api")
@Validated
public class SuplierController {
    @Autowired
    private SuplierService service;

    @PostMapping("/addSuplier")
    public ResponseEntity<?> postMethodName(@Valid @RequestBody Suplier suplier) {
        Suplier s = service.addSuplier(suplier);
        return JavaResponse.success(s);
    }

    @GetMapping("/getSuplier")
    public ResponseEntity<?> getSuplier() {
        ArrayList<Suplier> data = service.getSuplier();
        return JavaResponse.success(data);
    }


    
    
}
