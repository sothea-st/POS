package com.example.pos.service.RoleAndPermissionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.pos.entity.role.Role;
import com.example.pos.entity.role.roleProjection.RoleProjection;
import com.example.pos.repository.roleAndPermissionRepository.RoleRepository;
import com.example.pos.util.exception.customeException.JavaNotFoundByIdGiven;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
     @Autowired
     private RoleRepository repo;

     public Role add(Role role) {
          Role r = new Role();
          r.setRoleName(role.getRoleName());
          r.setCreateBy(role.getCreateBy());
          repo.save(r);
          return r;
     }

     public List<RoleProjection> getRole() {
          return repo.getRole();
     }

     public Role getRoleById(int id) {
          Role data = repo.getRoleById(id);
          if (data == null)
               throw new JavaNotFoundByIdGiven();
          return data;
     }

     public void deleteRoleById(int id, Role role) {
          Role data = repo.getRoleById(id);
          if (data == null)
               throw new JavaNotFoundByIdGiven();
          data.setStatus(role.isStatus());
          data.setDeleted(role.isDeleted());
          repo.save(data);
     }

     public Role updateRoleById(int id, Role role) {
          Role data = repo.getRoleById(id);
          if (data == null)
               throw new JavaNotFoundByIdGiven();
          data.setRoleName(role.getRoleName());
          repo.save(data);
          return data;
     }

}
