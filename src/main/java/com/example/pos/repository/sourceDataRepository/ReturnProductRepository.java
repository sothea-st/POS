package com.example.pos.repository.sourceDataRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.pos.entity.Product;
import com.example.pos.entity.sourceData.ReturnProduct;
import com.example.pos.repository.productProjection.ProductProjection;

@Repository
public interface ReturnProductRepository  extends JpaRepository<ReturnProduct,Integer>{
     @Query(nativeQuery = true , value = " select id,cat_id ,brand_id ,flag ,weight ,pro_image_name ,barcode ,pro_name_en ,pro_name_kh ,cost,price,product_status ,discount  from pos_product pp where barcode = ?")
     ProductProjection getProductByBarcode(String barcode);
}
