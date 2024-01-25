package com.example.pos.entity;

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
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pos_close_shift")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CloseShift {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int id;

     // @Column(name = "user_id")
     // private int userId;

     @Column(name = "user_code",length = 50)
     private String userCode;

     @Column(name = "close_time",length = 50)
     private String closeTime;

     @Column(name = "close_date",length = 20)
     private String closeDate;

     @Column(name = "express", precision = 10, scale = 2)
     private BigDecimal express;

     @Column(name = "khqr_mnk", precision = 10, scale = 2)
     private BigDecimal khqrMnk;

     @Column(name = "khqr_aba", precision = 10, scale = 2)
     private BigDecimal khqrAba;

     @Column(name = "credit_card", precision = 10, scale = 2)
     private BigDecimal creditCard;

     @Column(name = "cash_khr",precision = 10,scale = 0)
     private BigDecimal cashKhr;

     @Column(name = "cash_usd", precision = 10, scale = 2)
     private BigDecimal cashUsd;

     @Column(name = "create_by")
     private int createBy;

     @CreationTimestamp
     @Column(name = "create_date")
     private Date createDate;

     @Column(name = "status")
     private boolean status = true;

     @Column(name = "is_deleted")
     private boolean isDeleted = false;

}
