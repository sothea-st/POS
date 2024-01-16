package com.example.pos.repository;

import com.example.pos.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    @Query(nativeQuery = true,value = "select * from pos_category  where  status=true and is_deleted=false and parent_id=? order by move_position asc")
    ArrayList<Category> getCategory(int parentId);

    boolean existsByCatNameKh(String catNameKh);
    boolean existsByCatNameEn(String catNameKh);

    @Query(nativeQuery = true,value = "select * from pos_category where status=true and is_deleted=false and id=?")
    Category getCategoryById(int id);

    @Query(nativeQuery = true , value = "select count(*) from pos_category")
    int countLengthRow();


}
