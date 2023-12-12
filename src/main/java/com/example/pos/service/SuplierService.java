package com.example.pos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pos.constant.JavaValidation;
import com.example.pos.entity.Suplier;
import com.example.pos.repository.SuplierRepository;
import java.util.*;
@Service
public class SuplierService {
    @Autowired
    private SuplierRepository repo;
    public Suplier addSuplier(Suplier s){
        boolean name = repo.existsByName(s.getName());
        JavaValidation.checkDataAlreadyExists(name);
        Suplier obj = new Suplier();
        obj.setName(s.getName());
        obj.setDob(s.getDob());
        obj.setAddress(s.getAddress());
        obj.setContact(s.getContact());
        repo.save(obj);
        return obj;
    }

    public ArrayList<Suplier> getSuplier(){
        return repo.getListSuplier();
    }

}
