package com.example.pos.repository.sourceDataRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pos.entity.sourceData.ReturnProduct;

@Repository
public interface ReturnProductRepository  extends JpaRepository<ReturnProduct,Integer>{
    
}
