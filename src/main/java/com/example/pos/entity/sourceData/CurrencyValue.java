package com.example.pos.entity.sourceData;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.Date;
import org.hibernate.annotations.CreationTimestamp;
@Entity
@Table(name = "pos_currency_value")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "currency_usd",precision = 10,scale = 2)
    private BigDecimal currencyUsd;

    @Column(name = "currency_khr",precision = 10)
    private BigDecimal currencyKhr;

    @Column(name = "create_by")
    private int createBy;

    @Column(name = "create_date")
    @CreationTimestamp
    private Date createDate;

    @Column(name = "status")
    private boolean status = true;

    @Column(name = "is_deleted")
    private boolean isDeleted = false;

}
