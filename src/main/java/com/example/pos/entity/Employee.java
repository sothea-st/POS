//package com.example.pos.entity;
//
//import com.example.pos.constant.JavaMessage;
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.hibernate.annotations.CreationTimestamp;
//
//import java.util.Date;
//
//@Entity
//@Table(name = "pos_employee")
//@AllArgsConstructor
//@Data
//@NoArgsConstructor
//public class Employee {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    @NotBlank(message = JavaMessage.required)
//    @NotNull(message = JavaMessage.required)
//    private String nameKh;
//
//    @NotBlank(message = JavaMessage.required)
//    @NotNull(message = JavaMessage.required)
//    private String nameEn;
//
//    @NotBlank(message = JavaMessage.required)
//    @NotNull(message = JavaMessage.required)
//    private String gender;
//
//    @NotBlank(message = JavaMessage.required)
//    @NotNull(message = JavaMessage.required)
//    private String dob;
//
//    @NotBlank(message = JavaMessage.required)
//    private int age;
//
//
//    private String startDate;
//    private String profile;
//
//
//
//    @NotBlank(message = JavaMessage.required)
//    @NotNull(message = JavaMessage.required)
//    @Column(length = 12)
//    private String contact;
//
//
//    private String address="N/A";
//
//    @Column(name = "create_by")
//    private int createBy;
//
//    @CreationTimestamp
//    @Column(updatable = false,name = "create_date")
//    private Date createDate;
//
//    @Column(name = "status")
//    private boolean status=true;
//
//    @Column(name = "is_deleted")
//    private boolean isDeleted=false;
//
//}
