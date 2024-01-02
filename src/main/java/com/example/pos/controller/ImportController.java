package com.example.pos.controller;

import com.example.pos.components.JavaResponse;
import com.example.pos.entity.Import;
import com.example.pos.entity.ImportDetail;
import com.example.pos.service.ImportService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/import")
@Validated
public class ImportController {
    @Autowired
    private ImportService service;

    @PostMapping
    public ResponseEntity<?> addImport(@Valid @RequestBody Import i){
//        System.out.println("data = " + impDetail);
        Import data = service.addImport(i);
        return JavaResponse.success(data);
    }



}
