package com.example.pos.service;

import com.example.pos.util.exception.customeException.JavaNotFoundByIdGiven;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pos.constant.JavaValidation;
import com.example.pos.entity.Supplier;
import com.example.pos.repository.SupplierRepository;

import jakarta.servlet.http.HttpSession;

import java.util.*;
@Service
public class SupplierService {
    @Autowired
    private SupplierRepository repo;
    @Autowired
    private HttpSession session;

    public Supplier addSupplier(Supplier s){
        Object id = session.getAttribute("idUser");
        
        boolean isContact = repo.existsByContact(s.getContact());
        JavaValidation.phoneAlreadyExist(isContact);

        Supplier obj = new Supplier();
        obj.setNameKh(s.getNameKh());
        obj.setNameEn(s.getNameEn());
        obj.setAddress(s.getAddress());
        obj.setContact(s.getContact());
        obj.setCreateBy((Integer) id);
        repo.save(obj);
        return obj;
    }

    public ArrayList<Supplier> getSupplier(){
        return repo.getListSupplier();
    }

    public Supplier getSupplierById(int id){
        return repo.getSupplierById(id);
    }

    public Supplier updateSupplier(int id,Supplier s){

        Object idUser = session.getAttribute("idUser");
        Optional<Supplier> data = repo.findById(id);
        Supplier obj = data.get();


        if (!Objects.equals(obj.getContact(), s.getContact())) {
            boolean isContact = repo.existsByContact(s.getContact());
            JavaValidation.phoneAlreadyExist(isContact);
        }

        obj.setNameKh(s.getNameKh());
        obj.setNameEn(s.getNameEn());
        obj.setAddress(s.getAddress());
        obj.setContact(s.getContact());
        repo.save(obj);
        return obj;
    }

    public void deleteSupplier(int id,Supplier s){
        Optional<Supplier> data = repo.findById(id);
        if( data == null ) throw new JavaNotFoundByIdGiven();
        Supplier obj = data.get();
        obj.setDeleted(s.isDeleted());
        obj.setStatus(s.isStatus());
        repo.save(obj);
    }

}
