package com.example.pos.repository;

import com.example.pos.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;
@Repository
public interface SaleRepository extends JpaRepository<Sale,Integer> {
    @Query(nativeQuery = true , value = "select * from pos_sale ps where ps.emp_id = ? and ps.sale_date = ? order by id asc")
    List<Sale> getSale(int empId,String date);
}
