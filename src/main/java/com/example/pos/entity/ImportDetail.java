package com.example.pos.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "pos_import_detail")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImportDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "pro_id",length = 30)
    private int productId;

    @Column(name = "imp_id",length = 30)
    private int impId;

    @Column(name = "qty_new",length = 30)
    private int qtyNew=0;

    @Column(name = "qty_old",length = 30)
    private int qtyOld=0;

    @Column(name = "cost",precision = 10,scale = 2)
    private BigDecimal cost;

    @Column(name = "amount",precision = 10,scale = 2)
    private BigDecimal amount;

    @Column(name = "expired_date",length = 30)
    private String expireDate;

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
