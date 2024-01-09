package com.example.pos.repository.sourceDataRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pos.entity.sourceData.CancelItemDetail;

@Repository
public interface CancelItemDetialsRepository  extends JpaRepository<CancelItemDetail,Integer>{
     
}
