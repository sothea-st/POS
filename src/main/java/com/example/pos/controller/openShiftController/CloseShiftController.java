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
import com.example.pos.constant.JavaConstant;
import com.example.pos.constant.JavaValidation;
import com.example.pos.entity.CloseShift;
import com.example.pos.entity.OpenShift;
import com.example.pos.repository.shiftRepository.CloseShiftRepository;
import com.example.pos.repository.shiftRepository.OpenShiftRepository;
import com.example.pos.service.shiftService.CloseShiftService;

import jakarta.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api/closeShiftTime")
public class CloseShiftController {
    @Autowired
    private CloseShiftService service;

    @Autowired
    private HttpSession session;

    @Autowired
    private CloseShiftRepository repoClose;

    @Autowired
    private OpenShiftRepository repoOpen;


    @PostMapping
    public ResponseEntity<?> closeShift(@RequestBody CloseShift c) {
        HashMap<String,Object> map = new HashMap<>();
        var userId = session.getAttribute(JavaConstant.userCode);
        OpenShift countOpenShift = repoOpen.countOpenShift((String) userId, JavaConstant.currentDate);

        // protect when user try to processing sale but user does not open shift first
        if (countOpenShift == null || countOpenShift.getNumberOpenShift() == 0) {
            map.put(JavaConstant.message, JavaConstant.closeOpenShfitFirst);
            return JavaResponse.error(map);
        }
        
        CloseShift data = service.closeShift(c);
        return JavaResponse.success(data);
    }

    // @GetMapping("/cashierReport")
    // public ResponseEntity<?> getCashierReport(){
    //     return JavaResponse.success(service.cashierReport());
    // }


}
