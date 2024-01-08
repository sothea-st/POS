package com.example.pos.service.shiftService;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pos.constant.JavaConstant;
import com.example.pos.entity.OpenShift;
import com.example.pos.repository.shiftRepository.OpenShiftRepository;

import jakarta.servlet.http.HttpSession;
import java.util.*;
@Service
public class OpenShiftService {
     @Autowired
     private OpenShiftRepository repo;

     @Autowired
     private HttpSession session;

     public OpenShift openShift(OpenShift s){

          String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
          String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss a").format(Calendar.getInstance().getTime());

          Optional<List<OpenShift>> list = repo.getPosIdByCurrentDate(currentDate);
          int count = list.get().size();
          count++;
          String posId = "";
          if( count < 10 ) {
               posId= "0"+count;
          } else {
               posId=""+count;
          }
          
          Object userId = session.getAttribute(JavaConstant.userId);
          OpenShift data = new OpenShift();
          data.setPosId(posId);
          data.setUserId((Integer)userId);
          data.setReserveUsd(s.getReserveUsd());
          data.setReserveKhr(s.getReserveKhr());
          data.setOpenTime(timeStamp);
          data.setDate(currentDate);
          data.setCreateBy((Integer)userId);
          repo.save(data);
          return data;
     }

}
