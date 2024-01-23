package com.example.pos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;
import lombok.experimental.NonFinal;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Date;
import org.hibernate.annotations.CreationTimestamp;
import com.example.pos.constant.JavaMessage;

@Entity
@Table(name = "pos_company")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Company {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int id;

     @Column(name="company_name")
     @NotNull(message = JavaMessage.required)
     @NotBlank(message = JavaMessage.required)
     private String companyName;

     @Column(name = "contact")
     @NotNull(message = JavaMessage.required)
     @NotBlank(message = JavaMessage.required)
     private String contact;

     @Column(name = "address")
     private String address;

     @Column(name = "vattin")
     private String vattin;

     private String photo;

     @Column(name = "create_by")
     private int createBy;

     @CreationTimestamp
     @Column(name = "create_date")
     private Date createDate;

     @Column(name = "status")
     private boolean status=true;

     @Column(name = "is_deleted")
     private boolean isDeleted = false;

     
}
