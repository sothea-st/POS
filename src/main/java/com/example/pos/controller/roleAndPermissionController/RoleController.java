package com.example.pos.controller.roleAndPermissionController;

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
import com.example.pos.entity.role.Role;
import com.example.pos.entity.role.roleProjection.RoleProjection;
import com.example.pos.service.RoleAndPermissionService.RoleService;
import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/role")
public class RoleController {
     @Autowired
     private RoleService service;

     @PostMapping
     public ResponseEntity<?> add(@Valid @RequestBody Role r) {
          Role data = service.add(r);
          return JavaResponse.success(data);
     }

     @GetMapping
     public ResponseEntity<?> read(){
          List<RoleProjection> data = service.getRole();
          return JavaResponse.success(data);
     }

     @GetMapping("/{id}")
     public ResponseEntity<?> getRoleById(@PathVariable("id") int id) {
          Role data = service.getRoleById(id);
          return JavaResponse.success(data);
     }

     @DeleteMapping("/{id}")
     public ResponseEntity<?> deleteRole(@PathVariable("id") int id , @RequestBody Role role) {
          service.deleteRoleById(id, role);
          return JavaResponse.deleteSuccess(id);
     }

     @PutMapping("/{id}")
     public ResponseEntity<?> updateRole(@PathVariable("id") int id , @RequestBody Role role) {
          Role data = service.updateRoleById(id, role);
          return JavaResponse.success(data);
     }

}
