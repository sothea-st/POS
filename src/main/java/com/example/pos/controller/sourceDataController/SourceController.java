package com.example.pos.controller.sourceDataController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.example.pos.entity.sourceData.Source;
import com.example.pos.service.sourceDataService.SourceService;
import java.util.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/source")
public class SourceController {
     @Autowired
     private SourceService service;

     @PostMapping
     public ResponseEntity<?> addSource(@Valid @ModelAttribute Source s) {
          Source data = service.addSource(s);
          return JavaResponse.success(data);
     }

     @GetMapping
     public ResponseEntity<?> getSource() {
          List<Source> data = service.getSource();
          return JavaResponse.success(data);
     }

     @GetMapping("/{id}")
     public ResponseEntity<?> getSourceById(@PathVariable("id") int id) {
          Source data = service.getSourceById(id);
          return JavaResponse.success(data);
     }

     @DeleteMapping("/{id}")
     public ResponseEntity<?> deleteSource(@PathVariable("id") int id , @RequestBody Source s) {
          service.deleteSource(id, s);
          return JavaResponse.deleteSuccess(id);
     }

     @PutMapping("/{id}")
     public ResponseEntity<?> updateSource(@PathVariable("id") int id , @RequestBody Source s) {
          Source data = service.updateSource(id, s);
          return JavaResponse.success(data);
     }

}
