package com.example.pos.controller.openShiftController;

import org.aspectj.apache.bcel.classfile.Module.Open;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.pos.components.JavaResponse;
import com.example.pos.entity.OpenShift;
import com.example.pos.service.shiftService.OpenShiftService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/openShiftTime")
public class OpenShiftController {
     @Autowired
     private OpenShiftService service;
     
     @PostMapping
     public ResponseEntity<?> openShift(@Valid @RequestBody OpenShift o) {
          OpenShift data = service.openShift(o);
          return JavaResponse.success(data);
     }


     @GetMapping("/{userCode}")
     public ResponseEntity<?> getOpenShift(@PathVariable("userCode") String userCode) {
          OpenShift data = service.getOpenShift(userCode);
          return JavaResponse.success(data);
     }


}
