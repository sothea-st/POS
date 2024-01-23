package com.example.pos.entity;

import com.example.pos.constant.JavaMessage;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pos_import")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Import {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "imp_no",length = 50)
    private String impNo;

    @Column(name = "emp_id",length = 20)
    private int empId;

    @Column(name = "sub_id",length = 20)
    private int subId;

    @Column(name = "imp_date",length = 50)
    @NotNull(message = JavaMessage.required)
    @NotBlank(message = JavaMessage.required)
    private String impDate;

    @Column(name = "discount",length = 20)
    private int discount=0;

    @Column(name = "total",precision = 10,scale = 2)
    @NotNull(message = JavaMessage.required)
    private BigDecimal total;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ImportDetail> details;

    @Column(name = "create_by")
    private int createBy;

    @CreationTimestamp
    @Column(name = "created_date")
    private Date createDate;

    @Column(name = "status")
    private boolean status=true;

    @Column(name = "is_deleted")
    private boolean isDeleted=false;


}
