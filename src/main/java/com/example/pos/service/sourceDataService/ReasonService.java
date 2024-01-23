package com.example.pos.service.sourceDataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pos.constant.JavaConstant;
import com.example.pos.entity.sourceData.Reason;
import com.example.pos.repository.sourceDataRepository.ReasonRepository;
import com.example.pos.util.exception.customeException.JavaNotFoundByIdGiven;

import java.util.*;
import jakarta.servlet.http.HttpSession;

@Service
public class ReasonService {
     @Autowired
     private ReasonRepository repo;

     @Autowired
     private HttpSession session;

     public Reason addReason(Reason r) {
          Reason data = new Reason();
          var createBy = session.getAttribute(JavaConstant.userId);
          data.setReason(r.getReason());
          data.setCode(r.getCode());
          data.setCreateBy((Integer)createBy);
          repo.save(data);
          return data;
     }

     public List<Reason> getReason(){
          return repo.getReason();
     }

     public Reason getReasonById(int id) {
          Reason data = repo.getReasonById(id);
          if( data == null ) throw new JavaNotFoundByIdGiven();
          return repo.getReasonById(id);
     }

     public void delete(int id , Reason r) {
          Optional<Reason> data = repo.findById(id);
          Reason obj = data.get();
          obj.setStatus(r.isStatus());
          obj.setDeleted(r.isDeleted());
          repo.save(obj);
     }

     public Reason update(int id , Reason r) {
          Optional<Reason> data = repo.findById(id);
          Reason obj = data.get();
          obj.setReason(r.getReason());
          obj.setCode(r.getCode());
          repo.save(obj);
          return obj;
     }

     public List<Reason> getReasonByCode(String code) {
          return repo.getReasonByCode(code);
     }

}
