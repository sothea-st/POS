package com.example.pos.projections.customerProjection;

import java.math.BigDecimal;

public interface CustomerProjection {
    int getId();
    String getCus_name();
    String getCustomer_id();
    String getEmail();
    String getGender();
    String getContact();
    String getNationality();
    String getCoupon();
    int getCustomer_type_id();
    String getName();
}
