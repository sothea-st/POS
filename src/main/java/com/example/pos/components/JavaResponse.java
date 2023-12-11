package com.example.pos.components;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public class JavaResponse {
    public static ResponseEntity<?> success(Object data){
        return ResponseEntity.ok().body(Map.of("msg","success","data",data));
    }

}
