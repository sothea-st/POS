package com.example.pos.controller.openShiftController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.pos.components.JavaResponse;
import com.example.pos.constant.JavaValidation;
import com.example.pos.entity.CloseShift;
import com.example.pos.service.shiftService.CloseShiftService;
import java.util.*;

@RestController
@RequestMapping("/api")
public class CloseShiftController {
    @Autowired
    private CloseShiftService service;


    @PostMapping("/closeShiftTime")
    public ResponseEntity<?> closeShift(@RequestBody CloseShift c) {
        HashMap<String,Object> map = new HashMap<>();

        // String expressKey = "express";
        // String express = JavaValidation.checkField(""+c.getExpress(), expressKey);

        // String khqrMnkKey = "khqrMnk";
        // String khqrMnk = JavaValidation.checkField(""+c.getKhqrMnk(), khqrMnkKey);

        // String khqrAbaKey = "khqrAba";
        // String khqrAba = JavaValidation.checkField(""+c.getKhqrAba(), khqrAbaKey);

        // String creditCardKey = "creditCard";
        // String creditCard = JavaValidation.checkField(""+c.getCreditCard(), creditCardKey);

        // String cashKhrKey = "cashKhr";
        // String cashKhr = JavaValidation.checkField(""+c.getCashKhr(), cashKhrKey);

        // String cashUsdKey = "cashUsd";
        // String cashUsd = JavaValidation.checkField(""+c.getCashUsd(), cashUsdKey);

      
        // if( !express.isEmpty() )  map.put(expressKey, express);
        // if( !khqrMnk.isEmpty() )  map.put(khqrMnkKey, khqrMnk);
        // if( !khqrAba.isEmpty() )  map.put(khqrAbaKey, khqrAba);
        // if( !creditCard.isEmpty() )  map.put(creditCardKey, creditCard);
        // if( !cashKhr.isEmpty() )  map.put(cashKhrKey, cashKhr);
        // if( !cashUsd.isEmpty() )  map.put(cashUsdKey, cashUsd);

        // if( !map.isEmpty() ) return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);


        CloseShift data = service.closeShift(c);
        return JavaResponse.success(data);
    }

    // @GetMapping("/cashierReport")
    // public ResponseEntity<?> getCashierReport(){
    //     return JavaResponse.success(service.cashierReport());
    // }


}
