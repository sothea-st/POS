package com.example.pos.controller.branchController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pos.components.JavaResponse;
import com.example.pos.entity.branch.Branch;
import com.example.pos.service.branchService.BranchService;
import java.util.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/branch")

public class BranchController {
    @Autowired
    private BranchService service;
    
    @PostMapping
    public ResponseEntity<?> addBranch(@Valid @RequestBody Branch b) {
        Branch data = service.addBranch(b);
        return JavaResponse.success(data);
    }

    @GetMapping
    public ResponseEntity<?> getAllBranch(){
        List<Branch> data = service.getAllBranch();
        return JavaResponse.success(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBranchById(@PathVariable("id") int id) {
        Branch data = service.getBranchById(id);
        return JavaResponse.success(data);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<?> deleteBranch(@PathVariable("id") int id , @RequestBody Branch b) {
        service.deleteBranch(id, b);
        return JavaResponse.deleteSuccess(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBranch(@PathVariable("id") int id , @RequestBody Branch b) {
        Branch data = service.updateBranch(id, b);
        return JavaResponse.success(data);
    }
}
