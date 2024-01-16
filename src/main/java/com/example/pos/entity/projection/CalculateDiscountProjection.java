package com.example.pos.entity.projection;
import java.math.BigDecimal;
public interface CalculateDiscountProjection {
    int getQty();
    BigDecimal getPrice();
}
