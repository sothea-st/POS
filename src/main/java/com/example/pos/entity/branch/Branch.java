package com.example.pos.entity.branch;

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
import java.util.*;
@Entity
@Table(name = "pos_branch")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "branch_name_kh")
    @NotBlank(message = JavaMessage.required)
    @NotBlank(message = JavaMessage.required)
    private String branchNameKh;

    @Column(name = "branch_name_en")
    @NotBlank(message = JavaMessage.required)
    @NotBlank(message = JavaMessage.required)
    private String branchNameEn;
    
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
