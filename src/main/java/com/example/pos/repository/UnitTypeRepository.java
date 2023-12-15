package com.example.pos.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.pos.entity.Category;
import com.example.pos.entity.UnitType;
@Repository
public interface UnitTypeRepository extends JpaRepository<UnitType,Integer>{
    boolean existsByUnitTypeNameKh(String nameKh);
    boolean existsByUnitTypeNameEn(String nameEn);

    @Query(nativeQuery = true , value = "select * from pos_unit_type where status=true and is_deleted = false order by id desc")
    ArrayList<UnitType> getUnitType();

    @Query(nativeQuery = true,value = "select * from pos_unit_type where status=true and is_deleted=false and id=?")
    UnitType getUnitTypeById(int id);

}
