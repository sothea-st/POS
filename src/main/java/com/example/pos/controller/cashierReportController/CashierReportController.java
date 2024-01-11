package com.example.pos.controller.cashierReportController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.pos.components.JavaResponse;
import com.example.pos.service.cashierReport.CashierReportService;

@RestController
@RequestMapping("/api/cashierReport")
public class CashierReportController {
    @Autowired
    private CashierReportService service;

    @GetMapping()
    public ResponseEntity<?> getCashierReport(){
        return JavaResponse.success(service.cashierReport());
    }
}
