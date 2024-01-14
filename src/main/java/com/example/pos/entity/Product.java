package com.example.pos.entity;

import java.util.Date;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.CreationTimestamp;
import com.example.pos.constant.JavaMessage;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
@Entity
@Table(name = "pos_product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "cat_id")
    private int catId;
 
    private String flag;
    private String weight;
    private String costKhr;
    private String fileName;
    private String note;
    private String codeExpired;
    private String codeOutStock;
    private String priceKhr;
    private String barcode;

    @Column(name = "unit_type_id")
    private int unitTypeId;

    @Column(nullable = false, unique = true)
    @NotBlank(message = JavaMessage.required)
    private String proNameKh;

    @Column(nullable = false, unique = true)
    @NotBlank(message = JavaMessage.required)
    private String proNameEn;

    @Column(precision = 10, scale = 2,name = "cost_usd")
    private BigDecimal costUsd;


    @Column(precision = 10, scale = 2,name = "price_usd")
    private BigDecimal priceUsd;


    @Column(name = "product_status")
    private String productStatus;

    private BigDecimal discount;

    @Column(name = "discount_percentag",length = 20)
    private String discountPercentag;

    @Column(name = "status")
    private boolean status = true;

    @Column(name = "is_deleted")
    private boolean isDeleted = false;

    @CreationTimestamp
    @Column(updatable = false,name = "create_date")
    private Date createDate;

    @Column(name = "create_by")
    private int createBy;

}
