package com.example.pos.entity.sourceData;

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
@Table(name = "pos_default_price")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DefaultPrice {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int id;

     @Column(name = "user_id")
     private int userId;

     @Column(name = "default_price_usd",precision = 10 , scale = 2)
     private BigDecimal defaultPriceUsd;

     @Column(name = "defautl_price_khr",precision = 10 , scale = 0)
     private BigDecimal defaultPriceKhr;

     @Column(name = "status")
     private boolean status = true;

     @Column(name = "is_deleted")
     private boolean isDeleted = false;

     @CreationTimestamp
     @Column(updatable = false, name = "create_date")
     private Date createDate;

     @Column(name = "create_by")
     private int createBy;
}
