package com.example.pos.repository.shiftRepository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.pos.entity.OpenShift;
import java.util.*;
@Repository
public interface OpenShiftRepository extends JpaRepository<OpenShift,Integer> {

     @Query(nativeQuery = true , value = "select * from pos_open_shift where status = true and is_deleted = false and date = ?")
     Optional<List<OpenShift>> getPosIdByCurrentDate(String currentDate);
}  