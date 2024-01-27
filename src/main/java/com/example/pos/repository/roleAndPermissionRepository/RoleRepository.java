package com.example.pos.repository.roleAndPermissionRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

import com.example.pos.entity.role.Role;
import com.example.pos.entity.role.roleProjection.RoleProjection;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
     @Query(nativeQuery=true , value="select id,role_name from pos_role where status = true and is_deleted = false order by id desc")
     List<RoleProjection> getRole();

     @Query(nativeQuery = true , value = "select * from pos_role where status = true and is_deleted = false and id = ?")
     Role getRoleById(int id);

}
