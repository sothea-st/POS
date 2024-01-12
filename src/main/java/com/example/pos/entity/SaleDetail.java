package com.example.pos.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "pos_sale_details")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class SaleDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "sale_id")
    private int saleId;

    @Min(0)
    @Column(name = "pro_id")
    private int productId;

    @Column(name = "qty")
    private int qty=0;

    @Column(name = "price_usd" , precision = 10 , scale = 2)
    private BigDecimal priceUsd;

    @Column(name = "price_khr" , precision = 10)
    private BigDecimal priceKhr;

    @Column(name = "amount_usd" , precision = 10 , scale = 2)
    private BigDecimal amountUsd;

    @Column(name = "amount_khr" , precision = 10 , scale = 2)
    private BigDecimal amountKhr;

    @Column(name = "discount" , precision = 10 , scale = 2)
    private BigDecimal discount;

    @Column(name = "discount_percentag")
    private int discountPercentag=0;

    @Column(name = "create_by")
    @Min(0)
    private int createBy;

    @CreationTimestamp
    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "status")
    private boolean status=true;

    @Column(name = "is_delete")
    private boolean isDelete=false;

}
