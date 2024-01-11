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

    @Query(nativeQuery = true , value = "select pp.barcode,psd.qty ,psd.price ,psd.amount,pp.pro_name_en,psd.discount_percentag  from pos_sale_details psd inner join pos_product pp on pp.id = psd.pro_id  where psd.sale_id = ?")
    List<SaleDetailProjection> getDataDetail(int saleId);

    @Query(nativeQuery = true , value = "select sum(qty) from  pos_sale_details where sale_id in (?)")
    String sumQtySaled(List<Integer> saleId);

    @Query(nativeQuery = true , value = "select sum(amount) from  pos_sale_details where sale_id in (?)")
    String sumAmount(List<Integer> saleId);

    @Query(nativeQuery = true , value = "select sum(qty) from  pos_sale_details where sale_id in (?) and discount > 0")
    String sumQtyDiscount(List<Integer> saleId);

    @Query(nativeQuery = true , value = "select sum(discount) from  pos_sale_details where sale_id in (?) and discount > 0")
    String sumAmontDiscount(List<Integer> saleId);


    @Query(nativeQuery = true , value = "select sum(psd.qty) from pos_sale ps inner join pos_sale_details psd on ps.id = psd.sale_id  where ps.id in (?) and ps.total_usd > 0")
    String sumQtyPaymentByUsd(List<Integer> saleId);

    @Query(nativeQuery = true , value = "select sum(ps.total_usd) from pos_sale ps inner join pos_sale_details psd on ps.id = psd.sale_id  where ps.id in (?) and ps.total_usd > 0")
    String sumAmountPaymentByUsd(List<Integer> saleId);


    @Query(nativeQuery = true , value = "select sum(cast(ps.total_khr as decimal(10,2) ))  from pos_sale ps inner join pos_sale_details psd on ps.id = psd.sale_id  where ps.id in (?) and ps.total_khr != '0'")
    String sumAmountPaymentByKhr(List<Integer> saleId);

    @Query(nativeQuery = true , value = "select sum(psd.qty) from pos_sale ps inner join pos_sale_details psd on ps.id = psd.sale_id  where ps.id in (?) and ps.total_khr != '0'")
    String sumQtyPaymentByKhr(List<Integer> saleId);


    @Query(nativeQuery = true , value = "select sum(qty) from pos_sale_details psd where sale_id in (?) and discount_percentag = ?")
    String sumQtyDiscountPercentag(List<Integer> saleId , int discountPercentag);

    @Query(nativeQuery = true , value = "select sum(discount)  from pos_sale_details psd where sale_id in (?) and discount_percentag = ?")
    String sumAmountDiscountPercentag(List<Integer> saleId , int discountPercentag);

}
