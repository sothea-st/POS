package com.example.pos.controller.paymentController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pos.components.JavaResponse;
import com.example.pos.entity.payment.Payment;
import com.example.pos.service.paymentService.PaymentService;
import java.util.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;

@RestController
@RequestMapping("/api/payment")
@Validated
public class PaymentController {
    @Autowired
    private PaymentService service;


    @GetMapping("/{paymentNo}")
    public ResponseEntity<?> getData(@PathVariable("paymentNo") String paymentNo){
        var data = service.readData(paymentNo);
        return JavaResponse.success(data);
    }

    @GetMapping 
    public ResponseEntity<?> getData(){
        var data = service.readData("");
        return JavaResponse.success(data);
    }

}
