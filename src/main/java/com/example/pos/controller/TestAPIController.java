package com.example.pos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pos.components.JavaResponse;
import com.example.pos.constant.JavaController;

@RestController
@RequestMapping("api/public")
@CrossOrigin(origins = "http://localhost:3000") 
public class TestAPIController {
    @GetMapping("/hello")
    public ResponseEntity<?> test(){
        return JavaResponse.success("hello");
    }
}
