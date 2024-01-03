package com.example.pos.repository;

import com.example.pos.entity.ImportDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImportDetailRepository extends JpaRepository<ImportDetail,Integer> {
    @Query(nativeQuery = true,value = "select * from pos_import_detail pid where status =true and is_deleted =false and pid.qty_old > 0 and pid.pro_id = ? order by id desc limit 1")
    ImportDetail getDataImportDetail(int productId);


    @Query(nativeQuery = true,value = "update pos_import_detail set qty_old = ?  where status = true and is_deleted = false and pro_id = ? and imp_id = ?")
    ImportDetail updateQtyByProIdAndImpId(int productId , int impId , int qty);

}
