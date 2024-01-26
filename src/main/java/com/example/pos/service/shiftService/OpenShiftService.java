package com.example.pos.service.shiftService;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.aspectj.apache.bcel.classfile.Module.Open;
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

     public OpenShift openShift(OpenShift s){
          OpenShift data = new OpenShift();
          data.setPosId(s.getPosId());
          data.setUserCode(s.getUserCode());
          data.setReserveUsd(s.getReserveUsd());
          data.setReserveKhr(s.getReserveKhr());
          data.setOpenTime(s.getOpenTime());
          data.setOpenDate(JavaConstant.currentDate);
          data.setCreateBy(s.getCreateBy());
          data.setNumberOpenShift(1);
          repo.save(data);
          return data;
     }

     public OpenShift getOpenShift(String userCode){
          OpenShift data = repo.countOpenShift(userCode, JavaConstant.currentDate);
          return data;
     }

}
