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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pos_open_shift")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpenShift {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int id;

     @Column(name = "pos_id")
     private String posId;

     @Column(name = "user_id")
     private int userId;

     @Column(name="open_time")
     private String openTime;

     @Column(name="date")
     private String date;

     @Column(name = "reserve_usd",precision = 10,scale = 2)
     private BigDecimal reserveUsd;

     @Column(name = "reserve_khr",precision = 10,scale = 2)
     private String reserveKhr;

     
     private Integer numberOpenShift=0;

     @Column(name="create_by")
     private int createBy;

     @CreationTimestamp
     @Column(name = "creatd_date")
     private Date createDate;

     @Column(name = "status")
     private boolean status =true;

     @Column(name = "is_deleted")
     private boolean isDeleted = false;

}
