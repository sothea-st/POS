package com.example.pos.entity.sourceData;

import org.hibernate.annotations.CreationTimestamp;

import com.example.pos.constant.JavaMessage;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
 
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.*;
@Entity
@Table(name = "pos_return_product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "payment_no")
    @NotBlank(message = JavaMessage.required)
    @NotNull(message = JavaMessage.required)
    private String paymentNo;

    @Column(name = "return_date")
    private String returnDate;

    @Column(name = "return_time")
    private String returnTime;

    @Column(name = "reason_id")
    private int reasonId;

    @Column(name = "return_amount_usd",precision = 10,scale = 2)
    private BigDecimal returnAmountUsd;

    @Column(name = "return_amount_khr",precision = 10,scale = 0)
    private BigDecimal returnAmountKhr;


    @OneToMany(cascade = CascadeType.ALL)
    private List<ReturnDetails> dataDetails;

    @Column(name = "create_by")
    private int createBy;

    @Column(name = "create_date")
    @CreationTimestamp
    private Date createDate;

    @Column(name = "status")
    private boolean status = true;

    @Column(name = "is_deleted")
    private boolean isDeleted = false;


}
