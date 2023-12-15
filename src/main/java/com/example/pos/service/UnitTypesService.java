package com.example.pos.service;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pos.constant.JavaValidation;
import com.example.pos.entity.Category;
import com.example.pos.entity.UnitType;
import com.example.pos.repository.UnitTypeRepository;
import com.example.pos.util.exception.customeException.JavaNotFoundByIdGiven;

import jakarta.servlet.http.HttpSession;

@Service
public class UnitTypesService {
    @Autowired
    private UnitTypeRepository repository;

    @Autowired
    private HttpSession session;

    public UnitType addUnitType(UnitType u) {
        boolean isExistKh = repository.existsByUnitTypeNameKh(u.getUnitTypeNameKh());
        JavaValidation.checkDataAlreadyExists(isExistKh);

        boolean isExistEn = repository.existsByUnitTypeNameEn(u.getUnitTypeNameEn());
        JavaValidation.checkDataAlreadyExists(isExistEn);

        Object id = session.getAttribute("idUser");

        UnitType obj = new UnitType();
        obj.setUnitTypeNameKh(u.getUnitTypeNameKh());
        obj.setUnitTypeNameEn(u.getUnitTypeNameEn());
        obj.setCreateBy((Integer) id);
        repository.save(obj);
        return obj;
    }

    public ArrayList getUnitType() {
        return repository.getUnitType();
    }

    public UnitType getUnitTypeById(int id) {
        UnitType data = repository.getUnitTypeById(id);
        if( data == null ) throw new JavaNotFoundByIdGiven();
        return data;
    }

    public UnitType updateUnitType(int id, String nameKh, String nameEn) {

        Optional<UnitType> data = repository.findById(id);
        UnitType obj = data.get();

        if (!Objects.equals(obj.getUnitTypeNameEn(), nameEn)) {
            boolean isExist = repository.existsByUnitTypeNameEn(nameEn);
            JavaValidation.checkDataAlreadyExists(isExist);
        }

        if (!Objects.equals(obj.getUnitTypeNameKh(), nameKh)) {
            boolean isExist = repository.existsByUnitTypeNameKh(nameKh);
            JavaValidation.checkDataAlreadyExists(isExist);
        }

        obj.setUnitTypeNameKh(nameKh);
        obj.setUnitTypeNameKh(nameEn);
        repository.save(obj);
        return obj;
    }

    public UnitType deleteUnitType(int id,boolean status , boolean isDeleted){
        Optional<UnitType> u = repository.findById(id);
        UnitType obj = u.get();
        obj.setDeleted(isDeleted);
        obj.setStatus(status);
        return repository.save(obj);
    }

}
