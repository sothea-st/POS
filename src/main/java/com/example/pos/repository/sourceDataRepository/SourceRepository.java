package com.example.pos.repository.sourceDataRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.example.pos.entity.sourceData.Source;

@Repository
public interface SourceRepository extends JpaRepository<Source,Integer>{
     @Query(nativeQuery = true , value = "select * from pos_source where status =  true and is_deleted =  false order by id desc")
     List<Source> getSource();

     @Query(nativeQuery = true , value = "select * from pos_source where status =  true and is_deleted =  false and id = ?")
     Source getSourceById(int id);
}
