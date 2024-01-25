package com.example.pos.controller.cashierReportController;

import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.pos.components.JavaResponse;
import com.example.pos.constant.JavaConstant;
import com.example.pos.entity.CloseShift;
import com.example.pos.entity.OpenShift;
import com.example.pos.repository.shiftRepository.CloseShiftRepository;
import com.example.pos.repository.shiftRepository.OpenShiftRepository;
import com.example.pos.service.cashierReport.CashierReportService;
import java.text.SimpleDateFormat;
import jakarta.servlet.http.HttpSession;
import java.util.*;
@RestController
@RequestMapping("/api/cashierReport")
public class CashierReportController {
    @Autowired
    private CashierReportService service;

    @Autowired
    private HttpSession session;

    @Autowired
    private OpenShiftRepository repoOpen;

    @Autowired
    private CloseShiftRepository repoClose;

    @GetMapping
    public ResponseEntity<?> getCashierReport(@RequestParam("userCode") String userCode,@RequestParam("userId") int userId){
     
        HashMap<String,Object> map = new HashMap<>();
      
        // OpenShift countOpenShift = repoOpen.countOpenShift((Integer) userCode, JavaConstant.currenDate);   
        CloseShift closeShift = repoClose.getCloseShift(userCode, JavaConstant.currentDate);
        // protect when user try to processing sale but user does not open shift first
        // if (closeShift == null) {
        //     map.put(JavaConstant.message, JavaConstant.msgCloseShift);
        //     return JavaResponse.error(map);
        // }

        return JavaResponse.success(service.cashierReport(userCode,userId));
    }
}
