package com.example.pos.entity;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import com.example.pos.constant.JavaMessage;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pos_category")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Category  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false,unique = true)
    @NotBlank(message =JavaMessage.required)
    private String catNameKh;

    @Column(nullable = false,unique = true)
    @NotBlank(message = JavaMessage.required)
    private String catNameEn;

    @Column(name = "parent_id")
    private int parentId=0;

    @Column(name = "status")
    private boolean status = true;

    @Column(name = "is_deleted")
    private boolean isDeleted = false;

    @CreationTimestamp
    @Column(updatable = false,name = "create_date")
    private Date createDate;

    @Column(name = "create_by")
    private int createBy;

}
