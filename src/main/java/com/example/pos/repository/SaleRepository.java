package com.example.pos.repository;

import com.example.pos.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Integer> {
        // @Query(nativeQuery = true , value = "select ps.id from pos_sale ps where
        // ps.user_id = ? and ps.sale_date = ? order by id asc")
        // List<Integer> getListSaleId(int empId,String date);

        @Query(nativeQuery = true, value = "select sum(psd.qty) from pos_sale ps \r\n" + //
                        "inner join pos_payment pp on pp.sale_id = ps.id \r\n" + //
                        "inner join pos_sale_details psd on psd.sale_id = pp.sale_id \r\n" + //
                        "where ps.user_id = ? and sale_date = ? and pp.receive_usd > 0 and pp.payment_type = 'cash'")
        String sumQtySaledByUsd(int empId, String date);

        @Query(nativeQuery = true, value = "select sum( pp.receive_usd  ) from pos_sale ps \r\n" + //
                        "inner join pos_payment pp on pp.sale_id = ps.id \r\n" + //
                        "where ps.user_id = ? and sale_date = ? and pp.receive_usd > 0 and pp.payment_type = 'cash'")
        String sumAmountSaledByUsd(int empId, String date);

        @Query(nativeQuery = true, value = "select sum( psd.qty ) from pos_sale ps \r\n" + //
                        "inner join pos_payment pp on pp.sale_id = ps.id \r\n" + //
                        "inner join pos_sale_details psd on psd.sale_id = pp.sale_id \r\n" + //
                        "where ps.user_id = ? and sale_date = ? and pp.receive_khr != '0' and pp.payment_type = 'cash'")
        String sumQtySaledByKhr(int empId, String date);

        @Query(nativeQuery = true, value = "select sum( cast( pp.receive_khr as decimal(10) ) ) from pos_sale ps \r\n" + //
                        "inner join pos_payment pp on pp.sale_id = ps.id \r\n" + //
                        "where ps.user_id = ? and sale_date = ? and pp.receive_khr != '0' and pp.payment_type = 'cash'")
        String sumAmountSaledByKhr(int empId, String date);

        // ================================================================================

        @Query(nativeQuery = true, value = "select sum( psd.qty ) from pos_sale ps \r\n" + //
                        "inner join pos_payment pp on pp.sale_id = ps.id \r\n" + //
                        "inner join pos_sale_details psd on psd.sale_id = pp.sale_id \r\n" + //
                        "where ps.user_id = ? and sale_date = ? and pp.receive_usd >0 and pp.payment_type = 'aba'")
        String totalCountQtyABA(int userId, String date);

        @Query(nativeQuery = true, value = "select sum( pp.receive_usd ) from pos_sale ps \r\n" + //
                        "inner join pos_payment pp on pp.sale_id = ps.id \r\n" + //
                        "where ps.user_id = ? and sale_date = ? and pp.receive_usd > 0 and pp.payment_type = 'aba'")
        String totalAmountABA(int userId, String date);

        @Query(nativeQuery = true, value = "select sum( psd.qty ) from pos_sale ps \r\n" + //
                        "inner join pos_payment pp on pp.sale_id = ps.id \r\n" + //
                        "inner join pos_sale_details psd on psd.sale_id = pp.sale_id \r\n" + //
                        "where ps.user_id = ? and sale_date = ? and pp.receive_usd > 0 and pp.payment_type = 'mhnk'")
        String totalCountQtyMNK(int userId, String date);

        @Query(nativeQuery = true, value = "select sum( pp.receive_usd  ) from pos_sale ps \r\n" + //
                        "inner join pos_payment pp on pp.sale_id = ps.id \r\n" + //
                        "where ps.user_id = ? and sale_date = ? and pp.receive_usd > 0 and pp.payment_type = 'mhnk'")
        String totalAmountMNK(int userId, String date);

        @Query(nativeQuery = true, value = "select sum( psd.qty ) from pos_sale ps \r\n" + //
                        "inner join pos_payment pp on pp.sale_id = ps.id \r\n" + //
                        "inner join pos_sale_details psd on psd.sale_id = pp.sale_id \r\n" + //
                        "where ps.user_id = ? and sale_date = ? and pp.receive_usd > 0 and pp.payment_type = 'express'")
        String totalCountQtyExpress(int userId, String date);

        @Query(nativeQuery = true, value = "select sum( pp.receive_usd  ) from pos_sale ps \r\n" + //
                        "inner join pos_payment pp on pp.sale_id = ps.id \r\n" + //
                        "where ps.user_id = ? and sale_date = ? and pp.receive_usd > 0 and pp.payment_type = 'express'")
        String totalAmountExpress(int userId, String date);

        @Query(nativeQuery = true, value = "select sum( psd.qty ) from pos_sale ps \r\n" + //
                        "inner join pos_payment pp on pp.sale_id = ps.id \r\n" + //
                        "inner join pos_sale_details psd on psd.sale_id = pp.sale_id \r\n" + //
                        "where ps.user_id = ? and sale_date = ? and pp.receive_usd > 0 and pp.payment_type = 'credit'")
        String totalCountQtyCredit(int userId, String date);

        @Query(nativeQuery = true, value = "select sum( pp.receive_usd  ) from pos_sale ps \r\n" + //
                        "inner join pos_payment pp on pp.sale_id = ps.id \r\n" + //
                        "where ps.user_id = ? and sale_date = ? and pp.receive_usd > 0 and pp.payment_type = 'credit'")
        String totalAmountCredit(int userId, String date);

}
