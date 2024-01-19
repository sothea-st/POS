package com.example.pos.entity;

import java.math.BigDecimal;
import java.util.Date;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

     @Column(name = "pos_id",length = 30)
     private String posId;

     @Column(name = "user_code",length = 30)
     private String userCode;

     @Column(name="open_time",length = 50)
     private String openTime;

     @Column(name="open_date",length = 30)
     private String openDate;

     @Column(name = "reserve_usd",precision = 10,scale = 2)
     private BigDecimal reserveUsd;

     @Column(name = "reserve_khr",precision = 10,scale = 2)
     private BigDecimal reserveKhr;

     @Column(name = "number_open_shift")
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
