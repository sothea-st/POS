package com.example.pos.controller.paymentController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.pos.components.JavaResponse;
import com.example.pos.service.paymentService.ReprintService;
 

@RestController
@RequestMapping("/api/reprint")
@Validated
public class ReprintController {
    @Autowired
    private ReprintService service;


    @GetMapping("/{userId}/{paymentNo}")
    public ResponseEntity<?> getData(@PathVariable("paymentNo") String paymentNo,@PathVariable("userId") int userId){
        var data = service.readData(paymentNo,userId);
        return JavaResponse.success(data);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getData(@PathVariable("userId") int userId){
        var data = service.readData("",userId);
        return JavaResponse.success(data);
    }

}
