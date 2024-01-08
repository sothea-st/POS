package com.example.pos.repository;

import com.example.pos.entity.SaleDetail;
import com.example.pos.entity.projection.SaleDetailProjection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;
@Repository
public interface SaleDetailsRepository extends JpaRepository<SaleDetail,Integer> {
    @Query(nativeQuery = true , value = "select psd.qty ,psd.price ,psd.amount,pp.pro_name_en  from pos_sale_details psd inner join pos_product pp on pp.id = psd.pro_id  where psd.sale_id = ?")
    List<SaleDetailProjection> getDataDetail(int saleId);
}
