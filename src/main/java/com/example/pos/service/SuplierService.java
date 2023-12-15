package com.example.pos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pos.constant.JavaValidation;
import com.example.pos.entity.Suplier;
import com.example.pos.repository.SuplierRepository;

import jakarta.servlet.http.HttpSession;

import java.util.*;
@Service
public class SuplierService {
    @Autowired
    private SuplierRepository repo;
    @Autowired
    private HttpSession session;

    public Suplier addSuplier(Suplier s){
        Object id = session.getAttribute("idUser");
        
        boolean isContact = repo.existsByContact(s.getContact());
        JavaValidation.phoneAlreadyExist(isContact);

        Suplier obj = new Suplier();
        obj.setNameKh(s.getNameKh());
        obj.setNameEn(s.getNameEn());
        obj.setAddress(s.getAddress());
        obj.setContact(s.getContact());
        obj.setCreateBy((Integer) id);
        repo.save(obj);
        return obj;
    }

    public ArrayList<Suplier> getSuplier(){
        return repo.getListSuplier();
    }

}
