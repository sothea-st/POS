package com.example.pos.entity.role.sub_permission;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pos_sub_permission")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubPermission {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int id;

     @Column(name = "sub_name")
     private String subName;

     @Column(name = "permission_id")
     private int permissiomId;

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
