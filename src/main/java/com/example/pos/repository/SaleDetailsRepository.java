package com.example.pos.repository;

import com.example.pos.entity.SaleDetail;

import com.example.pos.entity.projection.SaleDetailProjection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface SaleDetailsRepository extends JpaRepository<SaleDetail, Integer> {

        @Query(nativeQuery = true, value = "select pp.barcode,psd.qty ,psd.price_usd ,psd.amount_usd,pp.pro_name_en,psd.discount_percentag  from pos_sale_details psd inner join pos_product pp on pp.id = psd.pro_id  where psd.sale_id = ?")
        List<SaleDetailProjection> getDataDetail(int saleId);

        @Query(nativeQuery = true, value = "select sum(psd.discount) from pos_sale ps\r\n" + //
                        "inner join pos_sale_details psd on psd.sale_id = ps.id \r\n" + //
                        "where ps.user_id = ? and ps.sale_date = ? and psd.discount_percentag = ?")
        String totalAmount(int userId, String date, int discountPercentag);

        @Query(nativeQuery = true, value = "select sum(psd.qty) from pos_sale ps\r\n" + //
                        "inner join pos_sale_details psd on psd.sale_id = ps.id \r\n" + //
                        "where ps.user_id = ? and ps.sale_date = ? and psd.discount_percentag = ?")
        String totalQty(int userId, String date, int discountPercentag);

        @Query(nativeQuery = true, value = "select sum(psd2.qty) from pos_sale ps2 \r\n" + //
                        "inner join pos_sale_details psd2 on ps2.id = psd2.sale_id \r\n" + //
                        "where ps2.user_id = ? and ps2.sale_date = ?")
        String totalQtySale(int userId, String date);

        @Query(nativeQuery = true, value = "select sum(psd.amount_usd) from pos_sale ps\r\n" + //
                        "inner join pos_sale_details psd on psd.sale_id = ps.id \r\n" + //
                        "where ps.user_id = ? and ps.sale_date = ?")
        String totalAmount(int userId, String date);

        @Query(nativeQuery = true, value = "select sum(psd.qty)  from pos_sale ps\r\n" + //
                        "inner join pos_sale_details psd on psd.sale_id = ps.id\r\n" + //
                        "where ps.user_id = ? and ps.sale_date = ? and psd.discount_percentag > 0")
        String totalQtyDiscount(int userId, String date);

        @Query(nativeQuery = true, value = "select sum(psd.discount)  from pos_sale ps \r\n" + //
                        "inner join pos_sale_details psd on psd.sale_id = ps.id\r\n" + //
                        "where ps.user_id = ? and ps.sale_date = ? and psd.discount_percentag > 0")
        String totalAmountDiscount(int userID, String date);

        @Query(nativeQuery = true, value = "select sum(prd.retur_qty)  from pos_sale ps\r\n" + //
                        "inner join pos_payment pp on pp.sale_id = ps.id\r\n" + //
                        "inner join pos_return_product prp on prp.payment_no = pp.payment_no \r\n" + //
                        "inner join pos_return_details prd on prd.return_id = prp.id \r\n" + //
                        "where ps.user_id=? and ps.sale_date =? and pp.is_return = 'returned'")
        String totalReturnQty(int userId ,String date);

        @Query(nativeQuery = true, value = "select sum(prd.return_amount_usd)  from pos_sale ps\r\n" + //
                        "inner join pos_payment pp on pp.sale_id = ps.id\r\n" + //
                        "inner join pos_return_product prp on prp.payment_no = pp.payment_no \r\n" + //
                        "inner join pos_return_details prd on prd.return_id = prp.id \r\n" + //
                        "where ps.user_id=? and ps.sale_date = ? and pp.is_return = 'returned'")
        String totalReturnAmount(int userId ,String date);

}
