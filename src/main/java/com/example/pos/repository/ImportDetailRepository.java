package com.example.pos.repository;

import com.example.pos.entity.ImportDetail;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImportDetailRepository extends JpaRepository<ImportDetail,Integer> {
    @Query(nativeQuery = true,value = "select * from pos_import_detail pid where status =true and is_deleted =false and pid.qty_old > 0 and pid.pro_id = ? order by id desc limit 1")
    ImportDetail getDataImportDetail(int productId);

    @Query(nativeQuery = true,value = "select * from pos_import_detail pid where status =true and is_deleted =false and  pro_id =? order by id desc limit 1")
    Optional<ImportDetail> findByImpId(int proId);


}
