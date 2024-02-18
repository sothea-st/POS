package com.example.demotest.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demotest.entity.Product;
import com.example.demotest.repository.ProductRepo;
import com.example.demotest.service.ProductService;

import jakarta.validation.Valid;
import net.bytebuddy.utility.JavaConstant;
import java.util.*;
@RestController
@RequestMapping("/api/product")
public class ProductController {
     @Autowired
     private ProductService service;
     @Autowired
     private ProductRepo repo;

     @PostMapping
    public ResponseEntity<?> addProduct(@Valid @ModelAttribute Product product ,
                                        @RequestParam(value = "flagFile",required = false)MultipartFile flagFile,
                                        @RequestParam(value = "file",required = false)MultipartFile file
                                        ) throws IOException {

        Product data = service.addProduct(product,file,flagFile);
           return  ResponseEntity.ok().body(Map.of("msg","Success","data",data));
    }


    @GetMapping
    public ResponseEntity<?> getProduct(){
        List<Product> data = service.getProd();
        return  ResponseEntity.ok().body(Map.of("msg","Success","data",data));
    }

}
