package com.example.pos.entity.projection;

import java.math.BigDecimal;

public interface PaymentProjection {
    BigDecimal getTotal();
    BigDecimal getReceive_usd();
    String getReceive_khr();
    BigDecimal getChange_usd();
    String getChange_khr();
    BigDecimal getRemaining_usd();
    String getRemaining_khr();
    String getPayment_no();
    String getSale_date();
    String getCustomer_type();
    int getSale_id();
}
