package com.example.pos.entity.sourceData;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;

import com.example.pos.constant.JavaMessage;

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
@Table(name = "pos_reason")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reason {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int id;

     @NotBlank(message = JavaMessage.required)
     @NotNull(message = JavaMessage.required)
     private String reason;

     private int createBy;

     @CreationTimestamp
     private Date createDate;

     private boolean status=true;

     private boolean isDeleted=false;
     
}
