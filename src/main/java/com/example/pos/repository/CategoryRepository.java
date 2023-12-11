package com.example.pos.repository;

import com.example.pos.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    @Query(nativeQuery = true,value = "select * from tb_category where status='1'")
    ArrayList<Category> getCategory();

    boolean existsByCatName(String catName);


    @Query(nativeQuery = true,value = "select * from tb_category where status = 1 and id=?")
    Category getCategoryById(int id);
}
