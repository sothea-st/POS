package com.example.pos.service.sourceDataService;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pos.constant.JavaConstant;
import com.example.pos.entity.sourceData.CancelItem;
import com.example.pos.entity.sourceData.CancelItemDetail;
import com.example.pos.repository.sourceDataRepository.CancelItemDetialsRepository;
import com.example.pos.repository.sourceDataRepository.CancelItemRepository;
import java.util.*;
import jakarta.servlet.http.HttpSession;

@Service
public class CancelItemService {
     @Autowired
     private CancelItemRepository repo;

     @Autowired
     private HttpSession session;

     @Autowired
     private CancelItemDetialsRepository repoDetails;

     public void cancelAndDeleteItem(CancelItem c){
          var createBy = session.getAttribute(JavaConstant.userId);
          String time = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss a").format(Calendar.getInstance().getTime());
          String date = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
          String code = c.getListCancelDetail().size() == 1 ?  "delete" : "cancel";
          CancelItem data = new CancelItem();
          data.setCode(code);
          data.setReasonId(c.getReasonId());
          data.setCreateBy((Integer)createBy);
          data.setCancelTime(time);
          data.setCancelDate(date);
          repo.save(data);
          for( int i = 0 ; i < c.getListCancelDetail().size() ; i++ ) {
               CancelItemDetail d= new CancelItemDetail();
               d.setProId(c.getListCancelDetail().get(i).getProId());
               d.setCancelId(data.getId());
               repoDetails.save(d);
          }
     }

}
