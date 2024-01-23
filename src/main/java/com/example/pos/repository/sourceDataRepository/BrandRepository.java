package com.example.pos.repository.sourceDataRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;
import com.example.pos.entity.sourceData.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Integer> {
    boolean existsByBrandNameEn(String brandNameEn);
    boolean existsByBrandNameKh(String brandNameKh);

    @Query(nativeQuery = true , value = "select * from pos_brand where status = true and is_deleted = false order by id desc")
    List<Brand> getListBrand();

    @Query(nativeQuery = true , value = "select * from pos_brand where status = true and is_deleted = false and id = ?")
    Brand getBrandById(int id);


}
