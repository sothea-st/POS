package com.example.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.pos.entity.Suplier;
import java.util.*;

@Repository
public interface SuplierRepository extends JpaRepository<Suplier,Integer> {
    boolean existsByName(String name);
     
    @Query(nativeQuery = true,value = "select * from tb_suplier where status ='1'")
    ArrayList<Suplier> getListSuplier();

}  