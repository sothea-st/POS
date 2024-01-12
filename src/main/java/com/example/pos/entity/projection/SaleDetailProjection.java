package com.example.pos.entity.projection;
import java.math.BigDecimal;
public interface SaleDetailProjection {
    int getQty();
    BigDecimal getPrice_usd();
    BigDecimal getAmount_usd();
    String getPro_name_en(); // pro_name_en is column from table product 
    String getBarcode();
    int getDiscount_percentag();
}  
