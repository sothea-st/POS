package com.example.pos.authentication.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
//@RequestMapping("/users1")
@RestController
public class TestController {

    @GetMapping("/hello")
    public ResponseEntity<?> hello(){
        return ResponseEntity.ok().body(Map.of("status","hello"));
    }
}
