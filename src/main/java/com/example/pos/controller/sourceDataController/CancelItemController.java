package com.example.pos.controller.sourceDataController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pos.components.JavaResponse;
import com.example.pos.entity.sourceData.CancelItem;
import com.example.pos.service.sourceDataService.CancelItemService;
import java.util.*;
@RestController
@RequestMapping("/api/cancelItem")
public class CancelItemController {
     @Autowired
     private CancelItemService service;

     @PostMapping
     public ResponseEntity<?> cancelItem(@RequestBody CancelItem c) {
          service.cancelAndDeleteItem(c);
          return JavaResponse.success("succes delelte item");
     }
}
