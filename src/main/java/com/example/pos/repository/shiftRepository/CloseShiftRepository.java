package com.example.pos.repository.shiftRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.pos.entity.CloseShift;
import java.util.*;
@Repository
public interface CloseShiftRepository extends JpaRepository<CloseShift,Integer> {
    // @Query(nativeQuery = true , value = "select * from pos_close_shift pcs where status = true and is_deleted = false and user_id = ? and close_date =?")
    @Query(nativeQuery = true , value = "select * from pos_close_shift pos where user_id = ? and close_date  = ? order by id desc limit 1")
    CloseShift getCloseShift(int userId,String date);


    @Query(nativeQuery = true ,value = "select count(*) from pos_close_shift where user_id = ? and close_date=?")
    int countCloseShift(int userId , String date);
}
