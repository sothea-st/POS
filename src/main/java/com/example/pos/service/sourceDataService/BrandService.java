package com.example.pos.service.sourceDataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pos.constant.JavaValidation;
import com.example.pos.entity.branch.Branch;
import com.example.pos.entity.sourceData.Brand;
import com.example.pos.repository.sourceDataRepository.BrandRepository;
import com.example.pos.util.exception.customeException.JavaNotFoundByIdGiven;

import java.util.*;
@Service
public class BrandService {
    @Autowired
    private BrandRepository repo;

    public Brand add(Brand b) {
        boolean isExistEn = repo.existsByBrandNameEn(b.getBrandNameEn());
        boolean isExistKh = repo.existsByBrandNameKh(b.getBrandNameKh());
        JavaValidation.checkDataAlreadyExists(isExistEn);
        JavaValidation.checkDataAlreadyExists(isExistKh);
        Brand data = new Brand();
        data.setBrandNameKh(b.getBrandNameKh());
        data.setBrandNameEn(b.getBrandNameEn());
        data.setCreateBy(b.getCreateBy());
        repo.save(data);
        return data;
    }

    public List<Brand> read(){
        return repo.getListBrand();
    }

    public Brand getBrandById(int id) {
        Optional<Brand> data = repo.findById(id);
        if( data == null ) throw new JavaNotFoundByIdGiven();
        return data.get();
    }

    public void deleteBrand(int id , Brand b) {
        Optional<Brand> data = repo.findById(id);
        if( data == null ) throw new JavaNotFoundByIdGiven();
        Brand brand = data.get();
        brand.setStatus(b.isStatus());
        brand.setDeleted(b.isDeleted());
        repo.save(brand);
    }

    public Brand updateBrand(int id , Brand b) {
        Optional<Brand> data = repo.findById(id);
        if( data == null ) throw new JavaNotFoundByIdGiven();
      
        Brand brand = data.get();
        if( !Objects.equals(b.getBrandNameEn(), brand.getBrandNameEn()) ) {
            boolean isExistEn = repo.existsByBrandNameEn(b.getBrandNameEn());
            JavaValidation.checkDataAlreadyExists(isExistEn);
        }

        if( !Objects.equals(b.getBrandNameKh(), brand.getBrandNameKh()) ) {
            boolean isExistKh = repo.existsByBrandNameKh(b.getBrandNameKh());
            JavaValidation.checkDataAlreadyExists(isExistKh);
        }
        
        brand.setBrandNameEn(b.getBrandNameEn());
        brand.setBrandNameKh(b.getBrandNameKh());
    
        repo.save(brand);
        return brand;
    }

}
