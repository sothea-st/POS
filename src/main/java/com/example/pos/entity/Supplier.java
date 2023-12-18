package com.example.pos.entity;

import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import com.example.pos.constant.JavaMessage;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
// import java.util.Date;
import java.util.*;

@Entity
@Table(name = "pos_supplier")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable =false)
    @NotBlank(message = JavaMessage.required)
    private String nameKh;

    @Column(nullable = false)
    @NotBlank(message = JavaMessage.required)
    private String nameEn;
 

    private String address="N/A";

    @NotNull
    @NotBlank(message = JavaMessage.required)
    private String contact;
 
    @Column(name = "status")
    private boolean status = true;

    @Column(name = "is_deleted")
    private boolean isDeleted=false;

    @CreationTimestamp
    @Column(updatable = false,name = "create_date")
    private Date createdDate;

    @Column(name = "create_by")
    private int createBy;

}