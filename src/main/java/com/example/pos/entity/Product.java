package com.example.pos.entity;

import java.util.Date;

import jakarta.validation.constraints.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
    @NotNull(message = JavaMessage.required)
    private BigDecimal cost;


    @Column(precision = 10, scale = 2)
    @NotNull(message = JavaMessage.required)
    private BigDecimal price;

    private String image;
    private String fileName;
    private String note;
    private String codeExpired;
    private String codeAlmostExpired;
    private String codeOutStock;
    private String codeAlmostOutStock;

  
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
