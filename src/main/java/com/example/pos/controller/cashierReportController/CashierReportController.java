package com.example.pos.controller.cashierReportController;

import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping()
    public ResponseEntity<?> getCashierReport(){
        String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
        var userId = session.getAttribute(JavaConstant.userId);
        HashMap<String,String> map = new HashMap<>();

        int countOpenShift = repoOpen.countOpenShift((Integer) userId, currentDate);
        int countCloseShift = repoClose.countCloseShift((Integer) userId, currentDate);
        // protect whenever user trying to open cashier report but user does not close shift yet
        if( countOpenShift == 0 || countCloseShift == 0) {
            map.put("message", JavaConstant.messageCashierReport);
            return JavaResponse.error(map);
        }

        return JavaResponse.success(service.cashierReport());
    }
}
