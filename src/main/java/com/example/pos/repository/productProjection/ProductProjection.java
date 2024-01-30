package com.example.pos.repository.productProjection;

import java.math.BigDecimal;

public interface ProductProjection {
     int getId();
     int getCat_id();
     int getBrand_id();
     String getFlag();
     String getWeight();
     String getPro_image_name();
     String getBarcode();
     String getPro_name_kh();
     String getPro_name_en();
     double getCost();
     double getPrice();
     String getProduct_status();
     int getDiscount();
     String getCode_out_stock();
     String getCode_expired();
}
