package com.example.pos.controller;

import com.example.pos.components.JavaResponse;
import com.example.pos.constant.JavaConstant;
import com.example.pos.entity.OpenShift;
import com.example.pos.entity.Sale;
import com.example.pos.entity.people.Customer;
import com.example.pos.repository.shiftRepository.OpenShiftRepository;
import com.example.pos.service.SaleService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api/sale")
@Validated
public class SaleController {
    @Autowired
    private SaleService service;
    @Autowired
    private HttpSession session;
    @Autowired
    private OpenShiftRepository repoOpen;

    @PostMapping 
    public ResponseEntity<?> saleProduct(@Valid @RequestBody Sale s) throws Exception {
        var userCode = session.getAttribute(JavaConstant.userCode);
     
        OpenShift countOpenShift = repoOpen.countOpenShift(s.getUserCode(), JavaConstant.currentDate);
        HashMap<String, Object> map = new HashMap<>();
        // protect when user try to processing sale but user does not open shift first
        if (countOpenShift == null || countOpenShift.getNumberOpenShift() == 0) {
            map.put(JavaConstant.message, JavaConstant.openShift);
            return JavaResponse.error(map);
        }
        var data = service.saleProduct(s);
        return JavaResponse.success(data);
    }
}
