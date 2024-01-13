package com.example.pos.service.sourceDataService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pos.constant.JavaConstant;
import com.example.pos.entity.sourceData.CurrencyValue;
import com.example.pos.repository.sourceDataRepository.CurrencyValueRepository;
import com.example.pos.util.exception.customeException.JavaNotFoundByIdGiven;

import java.util.*;
import jakarta.servlet.http.HttpSession;

@Service
public class CurrencyValueService {
    @Autowired
    private CurrencyValueRepository repo;

    @Autowired 
    private HttpSession session;

    public CurrencyValue addCurrency(CurrencyValue c) {
        CurrencyValue currency = new CurrencyValue();
        var createBy = session.getAttribute(JavaConstant.userId);

        currency.setCurrencyKhr(c.getCurrencyKhr());
        currency.setCurrencyUsd(c.getCurrencyUsd());
        currency.setCreateBy((Integer)createBy);
        repo.save(currency);
        return currency;
    }

    public List<CurrencyValue> getAllCurrencyValue(){
        return repo.getAllCurrencyValue();
    }

    public CurrencyValue getCurrencyById(int id) {
        return repo.getCurrencyById(id);
    }

    public void deleteCurrency(int id , CurrencyValue c){
        Optional<CurrencyValue> cc = repo.findById(id);
        if( cc == null ) throw new JavaNotFoundByIdGiven();
        CurrencyValue data = cc.get();
        data.setStatus(c.isStatus());
        data.setDeleted(c.isDeleted());
        repo.save(data);
    }

    public CurrencyValue updateCurrency(int id , CurrencyValue c) {
        Optional<CurrencyValue> data = repo.findById(id);
        CurrencyValue d = data.get();
        d.setCurrencyKhr(c.getCurrencyKhr());
        d.setCurrencyUsd(c.getCurrencyUsd());
        repo.save(d);
        return d;
    }


}
