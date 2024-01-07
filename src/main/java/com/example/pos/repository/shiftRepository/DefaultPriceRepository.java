package com.example.pos.repository.shiftRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.example.pos.entity.sourceData.DefaultPrice;

import lombok.val;

@Repository
public interface DefaultPriceRepository extends JpaRepository<DefaultPrice,Integer> {
     @Query(nativeQuery = true , value = "select * from pos_default_price where status = true and is_deleted = false order by id desc")
     List<DefaultPrice> getListDefaultPrice();


     @Query(nativeQuery = true , value = "select * from pos_default_price where status = true and is_deleted = false and id = ? ")
     DefaultPrice getDefaultPriceById(int id);

}
