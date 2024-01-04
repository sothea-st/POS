package com.example.pos.authentication.repositories;

//import com.example.demo.security.entity.User;
import com.example.pos.authentication.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    @Query(nativeQuery = true,value = "select count(*) from pos_user")
    int userID();




}