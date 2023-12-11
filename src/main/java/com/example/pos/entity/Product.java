package com.example.pos.entity;

import com.example.pos.constant.JavaMessage;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Min(1)
    private int catId;
//    @Column(nullable = false)
    @NotBlank(message = JavaMessage.required)
    private String proName;
    @Min(0)
    private int qty;
    @DecimalMin("1.00")
    private double unitPrice;

    private double subTotal;
    private String image;
    private int status = 1;
}
