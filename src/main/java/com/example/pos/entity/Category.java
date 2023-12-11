package com.example.pos.entity;

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
@Table(name = "tb_category")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Category  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false,unique = true)
    @NotBlank(message =JavaMessage.required)
    private String catName;
    @Min(value = 0) //status must be greater than or equal to 1
    private int status=1;
}
