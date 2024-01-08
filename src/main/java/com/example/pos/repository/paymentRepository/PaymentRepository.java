package com.example.pos.repository.paymentRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;

import com.example.pos.entity.Company;
import com.example.pos.entity.payment.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Integer> {
    @Query(nativeQuery = true , value = " select count(*) from pos_payment pp")
    int countRecord();
 
}
