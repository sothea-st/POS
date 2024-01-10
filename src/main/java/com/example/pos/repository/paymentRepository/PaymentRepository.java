package com.example.pos.repository.paymentRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;

import com.example.pos.entity.Company;
import com.example.pos.entity.payment.Payment;
import com.example.pos.entity.projection.PaymentProjection;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Integer> {
    @Query(nativeQuery = true , value = "select count(*) from pos_payment pp")
    int countRecord();
 

    @Query(nativeQuery = true , value = "select \r\n" + //
            "pp.total_usd,pp.total_khr,\r\n" + //
            "pp.receive_usd,pp.receive_khr,\r\n" + //
            "pp.change_usd,pp.change_khr,\r\n" + //
            "pp.remaining_usd,pp.remaining_khr,\r\n" + //
            "pp.payment_no,ps.sale_date,\r\n" + //
            "pct.name as customer_type,pp.sale_id\r\n" + //
            "from pos_payment pp inner join pos_sale ps on ps.id = pp.sale_id \r\n" + //
            "inner join pos_customer_type pct on pct.id = pp.customer_type_id\r\n" + //
            "where pp.payment_no =?")
    PaymentProjection getPaymentDataWithPaymentNo(String paymentNo);



    @Query(nativeQuery = true , value = "select \r\n" + //
            "pp.total_usd,pp.total_khr,\r\n" + //
            "pp.receive_usd,pp.receive_khr,\r\n" + //
            "pp.change_usd,pp.change_khr,\r\n" + //
            "pp.remaining_usd,pp.remaining_khr,\r\n" + //
            "pp.payment_no,ps.sale_date,\r\n" + //
            "pct.name as customer_type,pp.sale_id\r\n" + //
            "from pos_payment pp inner join pos_sale ps on ps.id = pp.sale_id \r\n" + //
            "inner join pos_customer_type pct on pct.id = pp.customer_type_id\r\n" + //
            "order by pp.id desc limit 1")
    PaymentProjection getPaymentDataWithoutPaymentNo();


    @Query(nativeQuery = true , value = "select pp2.payment_no from pos_payment pp2 where sale_id = ?")
    String getPaymentNo(int saleId);


}
