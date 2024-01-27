package com.example.pos.entity.role;

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
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pos_role")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int id;
     @Column(name = "role_name")
     @NotBlank(message = JavaMessage.required)
     @NotNull(message = JavaMessage.required)
     private String roleName;

     @Column(name = "status")
     private boolean status = true;

     @Column(name = "is_deleted")
     private boolean isDeleted = false;

     @CreationTimestamp
     @Column(updatable = false, name = "create_date")
     private Date createDate;

     @Column(name = "create_by")
     private int createBy;

}
