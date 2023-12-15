package com.example.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.pos.entity.Suplier;
import java.util.*;

@Repository
public interface SuplierRepository extends JpaRepository<Suplier,Integer> {
 
    boolean existsByContact(String contact);

    @Query(nativeQuery = true,value = "select * from pos_suplier where status =true and is_deleted=false")
    ArrayList<Suplier> getListSuplier();

}  