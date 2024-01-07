package com.example.pos.entity.sourceData;

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
@Table(name = "pos_customer_type")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerType {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int id;

     @Column(name = "name")
     @NotNull(message = JavaMessage.required)
     @NotBlank(message = JavaMessage.required)
     private String name;

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
