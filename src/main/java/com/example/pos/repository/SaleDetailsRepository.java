package com.example.pos.repository;

import com.example.pos.entity.SaleDetail;
import com.example.pos.entity.projection.SaleDetailProjection;
import com.example.pos.entity.projection.SaleDetailsProjection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;
@Repository
public interface SaleDetailsRepository extends JpaRepository<SaleDetail,Integer> {
    @Query(nativeQuery = true , value = "select pp.barcode,psd.qty ,psd.price ,psd.amount,pp.pro_name_en  from pos_sale_details psd inner join pos_product pp on pp.id = psd.pro_id  where psd.sale_id = ?")
    List<SaleDetailProjection> getDataDetail(int saleId);


    // @Query(nativeQuery = true , value = "select * from pos_sale_details psd where sale_id = ?")
    // List<SaleDetail> getSaleDetails(int saleId);
    @Query(nativeQuery = true , value = "select sum(qty) from  pos_sale_details where sale_id = ?")
    int sumQty(int saleId);

    @Query(nativeQuery = true , value = "select sum(amount) from  pos_sale_details where sale_id = ?")
    BigDecimal sumAmount(int saleId);

    @Query(nativeQuery = true , value = "select sum(discount) from  pos_sale_details where sale_id = ?")
    BigDecimal sumDiscount(int saleId);
  
}
