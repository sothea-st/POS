package com.example.pos.repository;

import com.example.pos.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Integer> {

        @Query(nativeQuery = true, value = "  select sum(psd.qty) from pos_sale ps \r\n" + //
                        "        inner join pos_payment pp on pp.sale_id = ps.id\r\n" + //
                        "        inner join pos_sale_details psd on psd.sale_id = pp.sale_id \r\n" + //
                        "        inner join pos_open_shift pos on pos.pos_id = ps.pos_id \r\n" + //
                        "        where ps.user_id = ? and sale_date = ? and pp.receive_usd > 0\r\n" + //
                        "        and pp.payment_type = 'cash' and  pos.pos_id = ? and pos.open_date = ? and pos.user_code = ?")
        String sumQtySaledByUsd(int empId, String date,String posId,String openDate,String userCode);

        @Query(nativeQuery = true, value = "select sum( ps.total ) from pos_sale ps \r\n" + //
                        "inner join pos_payment pp on pp.sale_id = ps.id \r\n" + //
                        "inner join pos_open_shift pos on pos.pos_id = ps.pos_id  \r\n" + //
                        "where ps.user_id = ? and sale_date = ?\r\n" + //
                        "and pp.receive_usd > 0 and pp.payment_type = 'cash'\r\n" + //
                        "and pos.pos_id = ? and pos.open_date = ?")
        String sumAmountSaledByUsd(int empId, String date,String posId,String openDate);

        @Query(nativeQuery = true, value = " select sum( psd.qty ) from pos_sale ps \r\n" + //
                        "    inner join pos_payment pp on pp.sale_id = ps.id \r\n" + //
                        "    inner join pos_sale_details psd on psd.sale_id = pp.sale_id  \r\n" + //
                        "    inner join pos_open_shift pos on pos.pos_id = ps.pos_id \r\n" + //
                        "    where ps.user_id = ? and sale_date = ? and pp.receive_khr != '0' \r\n" + //
                        "    and pp.payment_type = 'cash'\r\n" + //
                        "    and  pos.pos_id = ? and pos.open_date = ? and pos.user_code = ?")
        String sumQtySaledByKhr(int empId, String date,String posId,String openDate,String userCode);

        @Query(nativeQuery = true, value = "select sum(ps.total)  from pos_sale ps \r\n" + //
                        "inner join pos_payment pp on pp.sale_id = ps.id \r\n" + //
                        "inner join pos_open_shift pos on pos.pos_id = ps.pos_id \r\n" + //
                        "where ps.user_id  = ? and ps.sale_date = ? \r\n" + //
                        "and pp.payment_type = 'cash' and pp.receive_khr  > 0\r\n" + //
                        "and  pos.pos_id = ? and pos.open_date = ?")
        String sumAmountSaledByKhr(int empId, String date,String posId,String openDate);

        // ================================================================================

        @Query(nativeQuery = true, value = "select sum( psd.qty ) from pos_sale ps \r\n" + //
                        "inner join pos_payment pp on pp.sale_id = ps.id  \r\n" + //
                        "inner join pos_sale_details psd on psd.sale_id = pp.sale_id  \r\n" + //
                        "inner join pos_open_shift pos on pos.pos_id = ps.pos_id \r\n" + //
                        "where ps.user_id = ? and sale_date = ? and pp.receive_usd >0\r\n" + //
                        "and pp.payment_type = 'aba'\r\n" + //
                        "and  pos.pos_id = ? and pos.open_date = ? and pos.user_code = ?")
        String totalCountQtyABA(int userId, String date,String posId,String openDate,String userCode);

        @Query(nativeQuery = true, value = " select sum( pp.receive_usd ) from pos_sale ps  \r\n" + //
                        "    inner join pos_payment pp on pp.sale_id = ps.id  \r\n" + //
                        "    inner join pos_open_shift pos on pos.pos_id = ps.pos_id \r\n" + //
                        "    where ps.user_id = ? and sale_date = ? and \r\n" + //
                        "    pp.receive_usd > 0 and pp.payment_type = 'aba'\r\n" + //
                        "    and  pos.pos_id = ? and pos.open_date = ?")
        String totalAmountABA(int userId, String date,String posId,String openDate);

        @Query(nativeQuery = true, value = "select sum(psd.qty)  from pos_sale ps  \r\n" + //
                        "inner join pos_payment pp on pp.sale_id = ps.id  \r\n" + //
                        "inner join pos_sale_details psd on psd.sale_id = pp.sale_id  \r\n" + //
                        "inner join pos_open_shift pos on pos.pos_id = ps.pos_id \r\n" + //
                        "where ps.user_id = ? and ps.sale_date = ? \r\n" + //
                        "and pp.payment_type = 'mnk' \r\n" + //
                        "and  pos.pos_id = ? and pos.open_date = ? and pos.user_code = ?")
        String totalCountQtyMNK(int userId, String date,String posId,String openDate,String userCode);

        @Query(nativeQuery = true, value = "select sum(ps.total)  from pos_sale ps \r\n" + //
                        "inner join pos_payment pp on pp.sale_id = ps.id  \r\n" + //
                        "inner join pos_sale_details psd on psd.sale_id = pp.sale_id  \r\n" + //
                        "inner join pos_open_shift pos on pos.pos_id = ps.pos_id \r\n" + //
                        "where ps.user_id = ? and ps.sale_date = ?  \r\n" + //
                        "and pp.payment_type = 'mnk' and pp.receive_usd > 0\r\n" + //
                        "and  pos.pos_id = ? and pos.open_date = ?")
        String totalAmountMNK(int userId, String date,String posId,String openDate);

        @Query(nativeQuery = true, value = "select sum( psd.qty ) from pos_sale ps  \r\n" + //
                        "inner join pos_payment pp on pp.sale_id = ps.id  \r\n" + //
                        "inner join pos_sale_details psd on psd.sale_id = pp.sale_id  \r\n" + //
                        "inner join pos_open_shift pos on pos.pos_id = ps.pos_id \r\n" + //
                        "where ps.user_id = ? and sale_date = ? and pp.payment_type = 'express'\r\n" + //
                        "and  pos.pos_id = ? and pos.open_date = ? and pos.user_code = ?")
        String totalCountQtyExpress(int userId, String date,String posId,String openDate,String userCode);

        @Query(nativeQuery = true, value = "select sum(ps.total)  from pos_sale ps \r\n" + //
                        "inner join pos_payment pp on pp.sale_id = ps.id\r\n" + //
                        "inner join pos_sale_details psd on psd.sale_id = pp.sale_id \r\n" + //
                        "inner join pos_open_shift pos on pos.pos_id = ps.pos_id \r\n" + //
                        "where ps.user_id = ? and ps.sale_date = ? \r\n" + //
                        "and pp.payment_type = 'express' and pp.receive_usd > 0\r\n" + //
                        "and  pos.pos_id = ? and pos.open_date = ?")
        String totalAmountExpress(int userId, String date,String posId,String openDate);

        @Query(nativeQuery = true, value = "select sum(psd.qty)  from pos_sale ps\r\n" + //
                        "inner join pos_payment pp on pp.sale_id = ps.id \r\n" + //
                        "inner join pos_sale_details psd on psd.sale_id = pp.sale_id \r\n" + //
                        "inner join pos_open_shift pos on pos.pos_id = ps.pos_id \r\n" + //
                        "where ps.user_id = ? and ps.sale_date = ? and pp.payment_type = 'credit'\r\n" + //
                        "and  pos.pos_id = ? and pos.open_date = ? and pos.user_code = ?")
        String totalCountQtyCredit(int userId, String date,String posId,String openDate,String userCode);

        @Query(nativeQuery = true, value = "select sum(ps.total)  from pos_sale ps \r\n" + //
                        "inner join pos_payment pp on pp.sale_id = ps.id  \r\n" + //
                        "inner join pos_sale_details psd on psd.sale_id = pp.sale_id \r\n" + //
                        "inner join pos_open_shift pos on pos.pos_id = ps.pos_id \r\n" + //
                        "where ps.user_id = ? and ps.sale_date = ?\r\n" + //
                        "and pp.payment_type = 'credit' and pp.receive_usd > 0\r\n" + //
                        "and  pos.pos_id = ? and pos.open_date = ?")
        String totalAmountCredit(int userId, String date,String posId,String openDate);

}
