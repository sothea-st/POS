package com.example.pos.entity.payment;

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
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pos_payment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int id;

     @Column(name = "payment_no")
     private String paymentNo;

     @Column(name = "sale_id")
     private int saleId;

     // @Column(name = "total_usd",precision = 10 , scale = 2)
     // private BigDecimal totalUsd;

     // @Column(name = "total_khr")
     // @NotNull(message = JavaMessage.required)
     // @NotBlank(message = JavaMessage.required)
     // private String totalKhr;

     @Column(name = "receive_usd",precision = 10 , scale = 2, nullable = false)
     private BigDecimal receiveUsd;

     @Column(name = "receive_khr")
     @NotNull(message = JavaMessage.required)
     @NotBlank(message = JavaMessage.required)
     private String receiveKhr;

     @Column(name = "remaining_usd",precision = 10 , scale = 2 ,nullable = true)
     private BigDecimal remainingUsd;

     @Column(name = "remaining_khr")
     @NotNull(message = JavaMessage.required)
     @NotBlank(message = JavaMessage.required)
     private String remainingKhr;

     @Column(name = "change_usd",precision = 10 , scale = 2)
     private BigDecimal changeUsd;

     @Column(name = "change_khr")
     @NotNull(message = JavaMessage.required)
     @NotBlank(message = JavaMessage.required)
     private String changeKhr;

     @Column(name = "payment_type")
     @NotNull(message = JavaMessage.required)
     @NotBlank(message = JavaMessage.required)
     private String paymentType;

     @Column(name = "customer_type_id")
     private int customerTypeId;

     @Column(name = "source_id")
     private int sourceId;


     @Column(name = "is_return")
     private String isReturn;

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
