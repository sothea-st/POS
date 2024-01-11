package com.example.pos.entity.sourceData;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pos_return_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "return_id")
    private int returnId;

    @Column(name = "pro_id")
    private int proId;

    @Column(name = "qty")
    private int qty;

    @Column(name="price",scale = 2,precision = 10)
    private BigDecimal price;

    @Column(name="amount",scale = 2,precision = 10)
    private BigDecimal amount;
 

}
