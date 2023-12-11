package com.example.pos.service;

import com.example.pos.constant.JavaMessage;
import com.example.pos.constant.JavaValidation;
import com.example.pos.entity.Category;
import com.example.pos.repository.CategoryRepository;
import com.example.pos.util.exception.customeException.JavaNotFoundByIdGiven;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repo;

    public Category saveCategory(Category c){
        boolean catName = repo.existsByCatName(c.getCatName());
        JavaValidation.checkDataAlreadyExists(catName); // check catName already exists or not
        Category obj = new Category();
        obj.setCatName(c.getCatName());
        repo.save(obj);
        return obj;
    }

    public ArrayList<Category> getCategory(){
        return repo.getCategory();
    }

    public Category updateCategory(int id,Category c){
       Optional<Category> data = repo.findById(id);
       Category obj = data.get();

       if(!Objects.equals(obj.getCatName(),c.getCatName()) ) {
           boolean isExist = repo.existsByCatName(c.getCatName());
           JavaValidation.checkDataAlreadyExists(isExist);
       }

       obj.setCatName(c.getCatName());
       repo.save(obj);
       return  obj;
    }


    public Category getCategoryById(int id){
        Category c = repo.getCategoryById(id);
        if( c == null ) throw  new JavaNotFoundByIdGiven();
        return c;
    }

    public Category deleteCategory(int id,int status){
        Optional<Category> data = repo.findById(id);
        Category obj = data.get();
        obj.setStatus(status);
        return  repo.save(obj);
    }

}
