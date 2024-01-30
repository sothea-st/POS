package com.example.pos.entity.people;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import com.example.pos.constant.JavaMessage;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pos_customer")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = JavaMessage.required)
    @NotNull(message = JavaMessage.required)
    private String cusName;

    private String contact;
    private String email;
    @NotBlank(message = JavaMessage.required)
    @NotNull(message = JavaMessage.required)
    private String customerId;
    private String gender;
    private String nationality;
    private String coupon;
    private BigDecimal earning;

    @Column(name = "customer_type_id")
    private int customerTypeId;

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
