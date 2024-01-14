package com.example.pos.entity.sourceData;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pos_return_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "return_id")
    private int returnId;

    @Column(name = "return_pro_id")
    private int proId;

    @Column(name = "retur_qty")
    private int qty;

    @Column(name="return_price_usd",scale = 2,precision = 10)
    private BigDecimal priceUsd;

    @Column(name="return_price_khr",scale = 0,precision = 10)
    private BigDecimal priceKhr;

    @Column(name="return_amount_usd",scale = 2,precision = 10)
    private BigDecimal amountUsd;

    @Column(name="return_amount_khr",scale = 0,precision = 10)
    private BigDecimal amountKhr;
}
