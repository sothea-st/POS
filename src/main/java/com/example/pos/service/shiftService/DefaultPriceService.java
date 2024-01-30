package com.example.pos.service.shiftService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pos.constant.JavaConstant;
import com.example.pos.entity.sourceData.DefaultPrice;
import com.example.pos.projections.defaultPriceProjection.DefaultPriceProjection;
import com.example.pos.repository.shiftRepository.DefaultPriceRepository;
import com.example.pos.util.exception.customeException.JavaNotFoundByIdGiven;

import java.util.*;
import jakarta.servlet.http.HttpSession;

@Service
public class DefaultPriceService {
     @Autowired
     private DefaultPriceRepository repo;

 

     public DefaultPrice addDefaultPrice(DefaultPrice d) {
          
          DefaultPrice dp = new DefaultPrice();
          dp.setUserId(d.getUserId());
          dp.setDefaultPriceKhr(d.getDefaultPriceKhr());
          dp.setDefaultPriceUsd(d.getDefaultPriceUsd());
          dp.setCreateBy(d.getCreateBy());
          repo.save(dp);
          return dp;
     }

     public List<DefaultPrice> getListDefaultPrice(){
          return repo.getListDefaultPrice();
     }

     public DefaultPriceProjection getDefaultPriceById(int id) {
          DefaultPriceProjection dp = repo.getDefaultPriceById(id);
          if( dp == null ) throw new JavaNotFoundByIdGiven();
          return dp;
     }

     public void deleteDefaultPriceById(int id,DefaultPrice d) {
          Optional<DefaultPrice> data = repo.findById(id);
          DefaultPrice dp  = data.get();
          dp.setStatus(d.isStatus());
          dp.setDeleted(d.isDeleted());
          repo.save(dp);
     }

     public DefaultPrice updateDefaultPrice(int id , DefaultPrice d) {
          System.out.println("fffffffff = " + d.getDefaultPriceKhr());
          Optional<DefaultPrice> data = repo.findById(id);
          DefaultPrice dp = data.get();
          dp.setDefaultPriceKhr(d.getDefaultPriceKhr());
          dp.setDefaultPriceUsd(d.getDefaultPriceUsd());
          dp.setCreateBy(d.getCreateBy());
          repo.save(dp);
          return dp;
     }



}
