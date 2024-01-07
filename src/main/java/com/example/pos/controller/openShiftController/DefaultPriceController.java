package com.example.pos.controller.openShiftController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.pos.components.JavaResponse;
import com.example.pos.entity.sourceData.DefaultPrice;
import com.example.pos.service.shiftService.DefaultPriceService;
import java.util.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/defaultPrice")
@Validated
public class DefaultPriceController {
     @Autowired
     private DefaultPriceService service;

     @PostMapping
     public ResponseEntity<?> addDefaultPrice(@Valid @ModelAttribute DefaultPrice d) {
          DefaultPrice data = service.addDefaultPrice(d);
          return JavaResponse.success(data);
     }

     @GetMapping
     public ResponseEntity<?> getListDefaultPrice(){
          List<DefaultPrice> data = service.getListDefaultPrice();
          return JavaResponse.success(data);
     }

     @GetMapping("/{id}")
     public ResponseEntity<?> getDefaltPriceById(@PathVariable("id") int id) {
          DefaultPrice data = service.getDefaultPriceById(id);
          return JavaResponse.success(data);
     }
     
     @PutMapping("/{id}")
     public ResponseEntity<?> updateDefaultPrice(@PathVariable("id") int id , @RequestBody DefaultPrice d) {
          DefaultPrice data = service.updateDefaultPrice(id, d);
          return JavaResponse.success(data);
     }

     @DeleteMapping("/{id}")
     public ResponseEntity<?> deleteDefaultPrice(@PathVariable("id") int id , @RequestBody DefaultPrice d) {
          service.deleteDefaultPriceById(id, d);
          return JavaResponse.deleteSuccess(id);
     }
 
}
