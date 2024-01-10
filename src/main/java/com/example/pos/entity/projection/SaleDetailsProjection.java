package com.example.pos.entity.projection;
import java.math.*;
public interface SaleDetailsProjection {
    BigDecimal getAmount();
    int getQty();
}
