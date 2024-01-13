package com.example.pos.repository.sourceDataRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.example.pos.entity.sourceData.CurrencyValue;
@Repository
public interface CurrencyValueRepository extends JpaRepository<CurrencyValue,Integer> {

    @Query(nativeQuery = true , value = "select * from pos_currency_value where status = true and is_deleted = false order by id desc")
    List<CurrencyValue> getAllCurrencyValue();

    @Query(nativeQuery = true , value = "select * from pos_currency_value where status = true and is_deleted = false and id = ?")
    CurrencyValue getCurrencyById(int id);

}
