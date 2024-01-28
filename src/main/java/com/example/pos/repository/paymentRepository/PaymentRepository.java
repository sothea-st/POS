package com.example.pos.repository.paymentRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;

import com.example.pos.entity.Company;
import com.example.pos.entity.payment.Payment;
import com.example.pos.entity.projection.PaymentProjection;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
        @Query(nativeQuery = true, value = "select count(*) from pos_payment pp")
        int countRecord();

        @Query(nativeQuery = true, value = "select ps.total,pp.receive_usd,pp.receive_khr,\r\n" + //
                        "pp.change_usd,pp.change_khr,\r\n" + //
                        "pp.remaining_usd,pp.remaining_khr,\r\n" + //
                        "pp.payment_no,ps.sale_date,\r\n" + //
                        "pct.name as customer_type,pp.sale_id\r\n" + //
                        "from pos_payment pp inner join pos_sale ps on ps.id = pp.sale_id \r\n" + //
                        "inner join pos_customer_type pct on pct.id = pp.customer_type_id\r\n" + //
                        "where pp.payment_no = ?")
        PaymentProjection getPaymentDataWithPaymentNo(String paymentNo);

        @Query(nativeQuery = true, value = "select ps.total,pp.receive_usd,pp.receive_khr,\r\n" + //
                        "pp.change_usd,pp.change_khr,\r\n" + //
                        "pp.remaining_usd,pp.remaining_khr,\r\n" + //
                        "pp.payment_no,ps.sale_date,\r\n" + //
                        "pct.name as customer_type,pp.sale_id\r\n" + //
                        "from pos_payment pp inner join pos_sale ps on ps.id = pp.sale_id \r\n" + //
                        "inner join pos_customer_type pct on pct.id = pp.customer_type_id\r\n" + //
                        "where ps.sale_date =?\r\n" + //
                        "order by pp.id desc limit 1")
        PaymentProjection getPaymentDataWithoutPaymentNo(String date);

 

        @Query(nativeQuery = true, value = "select pp.payment_no  from pos_sale ps\r\n" + //
                        "inner join pos_payment pp on pp.sale_id = ps.id \r\n" + //
                        "where ps.user_id = ? and ps.sale_date = ? order by pp.id asc limit 1")
        String getFirstPaymentNumber(int userId, String date);

        @Query(nativeQuery = true, value = "select pp.payment_no  from pos_sale ps\r\n" + //
                        "inner join pos_payment pp on pp.sale_id = ps.id \r\n" + //
                        "where ps.user_id = ? and ps.sale_date = ? order by pp.id desc limit 1")
        String getLastPaymentNumber(int userId, String date);


        @Query(nativeQuery = true , value = "select pp.* from pos_sale ps inner join  \r\n" + //
                        "pos_payment pp on pp.sale_id = ps.id \r\n" + //
                        "where payment_no = ?")
        Optional<Payment> getDataPayment(String paymentNo);

}
