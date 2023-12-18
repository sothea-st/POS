package com.example.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.pos.entity.Supplier;
import java.util.*;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Integer> {
 
    boolean existsByContact(String contact);

    @Query(nativeQuery = true,value = "select * from pos_supplier where status =true and is_deleted=false order by id desc")
    ArrayList<Supplier> getListSupplier();

    @Query(nativeQuery = true,value = "select * from pos_supplier where status=true and is_deleted=false and id =?")
    Supplier getSupplierById(int id);

}  