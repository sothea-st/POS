package com.example.pos.repository;

import com.example.pos.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    boolean existsByProName(String name);

    @Query(nativeQuery = true,value = "select * from tb_product where status=1")
    ArrayList<Product> getProduct();

    @Query(nativeQuery = true,value = "select count(*) from tb_product where status=1")
    int countRow();
    @Query(nativeQuery = true,value = "select * from tb_product where status=1 and id=?")
    Product getProductById(int id);

}
