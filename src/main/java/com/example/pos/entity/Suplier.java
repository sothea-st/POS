package com.example.pos.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.pos.constant.JavaMessage;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
// import java.util.Date;
import java.util.*;

@Entity
@Table(name = "tb_suplier")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Suplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable =false,unique = true )
    @NotBlank(message = JavaMessage.required)
    private String name;
    @NotBlank(message = JavaMessage.required)
    private String dob;

    private String address;
    @NotBlank(message = JavaMessage.required)
    private String contact;
 
    private String status="1";
 
    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

}