package com.example.pos.entity;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.pos.constant.JavaMessage;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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

    @Column(nullable = false)
    @NotBlank(message = JavaMessage.required)
    private String catId;

    @Column(nullable = false)
    @NotBlank(message = JavaMessage.required)
    private String unitTypeId;

    @Column(nullable = false, unique = true)
    @NotBlank(message = JavaMessage.required)
    private String proNameKh;

    @Column(nullable = false, unique = true)
    @NotBlank(message = JavaMessage.required)
    private String proNameEn;

    @Column(precision = 10, scale = 2)
    private BigDecimal cost;

    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    private String image;

    private String codeExpired="#EXPIRED";
    private String codeAlmostExpired ="#A_EXPIRED";
    private String codeOutStock = "#OUTSTOCK";
    private String codeAlmostOutStock = "#A_OUTSTOCK";

  
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
