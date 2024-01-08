package com.example.pos.entity.projection;
import java.math.BigDecimal;
public interface SaleDetailProjection {
    int getQty();
    BigDecimal getPrice();
    BigDecimal getAmount();
    String getPro_name_en(); // pro_name_en is column from table product 
}  
