package com.example.pos.controller.assignRoleController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.pos.authentication.entity.User;
import com.example.pos.authentication.repositories.UserRepository;
import com.example.pos.components.JavaResponse;
import com.example.pos.constant.JavaConstant;
import com.example.pos.entity.sourceData.AssignRole;
import com.example.pos.repository.roleAndPermissionRepository.RoleRepository;
import com.example.pos.service.RoleAndPermissionService.RoleService;

@RestController
@RequestMapping("/api/assignRole")
public class AssignRoleController {
    @Autowired
    private RoleService service;

    @Autowired  private RoleRepository repoRole;

    @Autowired private UserRepository repoUser;

    @PostMapping
    public ResponseEntity<?> assignRole(@RequestParam("assignerId")int assignerId , @RequestBody AssignRole a) {

        int roleId = repoUser.findById(assignerId).get().getRole();
    
        String roleName = repoRole.findById(roleId).get().getRoleName();
    
        System.out.println("a.getAssignerId() - " + roleName);
        if( !roleName.equals(JavaConstant.admin) ) {
            return JavaResponse.success("This account have no permission assign role!");
        }

        service.assignRole(a);
        return JavaResponse.success("Assign role success");
    }

}
