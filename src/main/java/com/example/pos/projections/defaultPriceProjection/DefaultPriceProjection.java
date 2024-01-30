package com.example.pos.projections.defaultPriceProjection;

import java.math.BigDecimal;

public interface DefaultPriceProjection {
    int getId();
    BigDecimal getDefault_price_usd();
    BigDecimal getdefault_price_khr();
}
