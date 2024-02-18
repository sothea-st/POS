package com.example.demotest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demotest.entity.Product;
import java.util.*;
@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {
     @Query(nativeQuery = true , value = "select * from pos_product")
     List<Product> getAll();
}
