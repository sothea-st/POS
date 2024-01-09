package com.example.pos.controller.sourceDataController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.pos.components.JavaResponse;
import com.example.pos.entity.sourceData.Reason;
import com.example.pos.service.sourceDataService.ReasonService;
import java.util.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/reason")
@Validated
public class ReasonController {
     @Autowired
     private ReasonService service;

     @PostMapping
     public ResponseEntity<?> addReason(@Valid @RequestBody Reason reason) {
          Reason data = service.addReason(reason);
          return JavaResponse.success(data);
     }

     @GetMapping
     public ResponseEntity<?> getReason() {
          List<Reason> data = service.getReason();
          return JavaResponse.success(data);
     }

     @GetMapping("/{id}")
     public ResponseEntity<?> getReasonById(@PathVariable("id") int id) {
          Reason data = service.getReasonById(id);
          return JavaResponse.success(data);
     }

     @DeleteMapping("/{id}")
     public ResponseEntity<?> delete(@PathVariable("id") int id,@RequestBody Reason r) {
          service.delete(id,r);
          return JavaResponse.deleteSuccess(id);
     }

     @PutMapping("/{id}")
     public ResponseEntity<?> update(@PathVariable("id") int id,@RequestBody Reason r) {
          Reason data = service.update(id,r);
          return JavaResponse.success(data);
     }

}
