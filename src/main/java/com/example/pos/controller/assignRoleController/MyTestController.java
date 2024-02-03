package com.example.pos.controller.assignRoleController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pos.components.JavaResponse;

@RestController
@RequestMapping("/api/public/testing")
public class MyTestController {
     @GetMapping
     public ResponseEntity<?> hello(){
          return JavaResponse.success("dddddddddddddd");
     }
}
