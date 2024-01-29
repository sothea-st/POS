package com.example.pos.authentication.repositories;

//import com.example.demo.security.entity.User;
import com.example.pos.authentication.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;


@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
//    Optional<User> findByEmail(String email);
    Optional<User>  findByUserCode(String userCode);

//    boolean existsByEmail(String email);

//    boolean existsByPhone(String phone);

    @Query(nativeQuery = true,value = "select count(*) from pos_user")
    int userCount();

    @Query(nativeQuery = true , value = "select pe.name_en  from pos_user pu inner join pos_employee pe on pu.emp_id = pe.id  where pe.status =true and pe.is_deleted =false and pu.status = true and pu.is_deleted =false and pu.id = ?")
    String getNameEmp(int id);

    @Query(nativeQuery = true,value = "select * from pos_user where status = true and is_deleted=false and id=?")
    User getUserById(int id);
 

}