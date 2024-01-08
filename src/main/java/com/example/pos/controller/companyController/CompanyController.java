package com.example.pos.controller.companyController;

import static org.springframework.util.MimeTypeUtils.IMAGE_PNG_VALUE;

import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.example.pos.components.JavaResponse;
import com.example.pos.entity.Company;
import com.example.pos.service.companyService.CompanyService;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyController {
     @Autowired
     private CompanyService service;

     @PostMapping
     public ResponseEntity<?> addCompany(@Valid @ModelAttribute Company c , @RequestParam(value = "file",required = false) MultipartFile file) throws IOException {
          Company data = service.addCompany(c, file);
          return JavaResponse.success(data);
     }

     @GetMapping
     public ResponseEntity<?> getCompnay(){
          List<Company> data = service.getCompany();
          return JavaResponse.success(data);
     }

     @GetMapping("/{id}")
     public ResponseEntity<?> getCompanyById(@PathVariable("id") int id) {
          Company data = service.getCompanyById(id);
          return JavaResponse.success(data);
     }

     @DeleteMapping("/{id}")
     public ResponseEntity<?> deleteCompany(@PathVariable("id") int id ,@RequestBody Company c ) {
          service.deleteCompany(id, c);
          return JavaResponse.deleteSuccess(id);
     }

     @PostMapping("/{id}")
     public ResponseEntity<?> updateCompany(@PathVariable("id") int id , @ModelAttribute Company c , @RequestParam(value = "file" , required = false) MultipartFile file) throws IOException {
          Company data = service.updateCompany(id, c, file);
          return JavaResponse.success(data);
     }

}
