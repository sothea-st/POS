package com.example.pos.entity;

import com.example.pos.constant.JavaMessage;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "pos_employee")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @NotBlank(message = JavaMessage.required)
    @NotNull(message = JavaMessage.required)
    @Column(name = "name_kh")
    private String nameKh;

    @NotBlank(message = JavaMessage.required)
    @NotNull(message = JavaMessage.required)
    @Column(name = "name_en")
    private String nameEn;

    @NotBlank(message = JavaMessage.required)
    @NotNull(message = JavaMessage.required)
    @Column(name = "gender")
    private String gender;

    @NotBlank(message = JavaMessage.required)
    @NotNull(message = JavaMessage.required)
    @Column(name = "dob")
    private String dob;

    @Column(name = "start_date")
    private String startDate;

    private String imageName;

    @NotBlank(message = JavaMessage.required)
    @NotNull(message = JavaMessage.required)
    @Column(length = 12)
    private String contact;


    private String address;

    @Column(name = "create_by")
    private int createBy;

    @CreationTimestamp
    @Column(updatable = false,name = "create_date")
    private Date createDate;

    @Column(name = "status")
    private boolean status=true;

    @Column(name = "is_deleted")
    private boolean isDeleted=false;

}
