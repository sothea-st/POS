package com.example.pos.controller;

import com.example.pos.components.JavaResponse;
import com.example.pos.constant.JavaValidation;
import com.example.pos.entity.Employee;
import com.example.pos.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.IMAGE_PNG_VALUE;

@RestController
@RequestMapping("/api/employee")
@Validated
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @PostMapping
    public ResponseEntity<?> addEmployee(@Valid @ModelAttribute Employee e, @RequestParam(value = "image",required = false)MultipartFile file) throws IOException {
        HashMap<String,String> err = new HashMap<>();
        String key="contact";
        String contact = JavaValidation.checkPhone(e.getContact());

        if( !contact.isEmpty() ) {
            err.put(key, contact);
            return ResponseEntity.status(500).body(err);
        }
        Employee data = service.addEmployee(e,file);


        return JavaResponse.success(data);
    }

    @GetMapping
    public ResponseEntity<?> getEmployee(){
        List<Employee> data = service.getEmployee();
        return JavaResponse.success(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable("id") int id){
        Employee data = service.getEmployeeById(id);
        return JavaResponse.success(data);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployeeById(@PathVariable("id") int id , @RequestBody Employee e) {
        service.deleteEmployeeById(id,e);
        return JavaResponse.deleteSuccess(id);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@Valid @PathVariable("id") int id ,@ModelAttribute Employee e , @RequestParam("image") MultipartFile file) throws IOException{
        HashMap<String,String> err = new HashMap<>();
        String key="contact";
        String contact = JavaValidation.checkPhone(e.getContact());

        if( !contact.isEmpty() ) {
            err.put(key, contact);
            return ResponseEntity.status(500).body(err);
        }
        Employee data = service.updateEmployee(id,e,file);
        return JavaResponse.success(data);
    }

    @GetMapping("/readFileById/{id}")
    public  ResponseEntity<byte[]>  getImage(@PathVariable("id") String id)throws IOException {
        byte[] data = service.getImageEmployee(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf(IMAGE_PNG_VALUE))
                .body(data);
    }



}
