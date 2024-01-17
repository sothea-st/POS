package com.example.pos.repository;

import com.example.pos.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    boolean existsByProNameKh(String name);
    boolean existsByProNameEn(String name);

    @Query(nativeQuery = true,value = "select * from pos_product where status=true and is_deleted=false order by id desc")
    ArrayList<Product> getProduct();

    @Query(nativeQuery = true,value = "select count(*) from pos_product where status=true and is_deleted=false")
    int countRow();
    @Query(nativeQuery = true,value = "select * from pos_product where status=true and is_deleted=false and id=?")
    Product getProductById(int id);

    @Query(nativeQuery = true , value = "select * from pos_product where status = true and is_deleted = false and cat_id = ?")
    List<Product> getProductByCatId(int catId);

}
