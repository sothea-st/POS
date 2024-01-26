package com.example.pos.repository.shiftRepository;
import java.util.Optional;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.pos.entity.OpenShift;
import java.util.*;
@Repository
public interface OpenShiftRepository extends JpaRepository<OpenShift,Integer> {

     @Query(nativeQuery = true , value = "select * from pos_open_shift where status = true and is_deleted = false and open_date = ?")
     Optional<List<OpenShift>> getPosIdByCurrentDate(String currentDate);

     @Query(nativeQuery = true , value = "select count(*) from pos_open_shift where status = true and is_deleted = false and open_date = ? and number_open_shift = 0")
     int countPosId(String currentDate);

     @Query(nativeQuery = true , value = "select * from pos_open_shift pos where status = true and is_deleted = false and user_code = ? and open_date = ? and pos_id = ? order by id desc limit 1")
     OpenShift getDataOpenShift(String userCode , String date,String posId);

     @Query(nativeQuery = true , value = "select * from pos_open_shift pos where status = true and is_deleted = false and user_code = ? and open_date = ? order by id desc limit 1")
     Optional<OpenShift> getNumberOpenShift(String userCode , String date);

     @Query(nativeQuery = true , value = "select * from pos_open_shift pos where user_code = ? and open_date  = ? order by id desc limit 1")
     OpenShift countOpenShift(String userCode, String date);
     
}  