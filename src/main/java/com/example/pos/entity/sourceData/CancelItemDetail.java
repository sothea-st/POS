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
 
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pos_cancel_detail")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CancelItemDetail {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int id;
     private int cancelId;
     private int proId;
}
