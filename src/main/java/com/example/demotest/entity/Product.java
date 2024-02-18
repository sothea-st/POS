// package com.example.demotest.entity;

 

// import java.util.Date;
// import jakarta.validation.constraints.*;
// import org.hibernate.annotations.CreationTimestamp;
 
// import jakarta.persistence.*;
// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;
// import java.math.BigDecimal;
// @Entity
// @Table(name = "pos_product")
// @Data
// @AllArgsConstructor
// @NoArgsConstructor
// public class Product {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private int id;

//     @Column(name = "cat_id")
//     private int catId;
    
//     @Column(name = "brand_id")
//     private int brandId;

//     @Column(name = "flag" , length = 150)
//     private String flag;

//     @Column(name = "weight" , length = 50)
//     private String weight;

//     @Column(name = "pro_image_name" , length = 255)
//     private String proImageName;

//     @Column(name = "node" , length = 500)
//     private String note;

//     @Column(name = "code_expired" , length = 30)
//     private String codeExpired;

//     @Column(name = "code_out_stock" , length = 30)
//     private String codeOutStock;

//     @Column(name = "barcode",length = 100)
//     private String barcode;

//     // @Column(name = "unit_type_id",length = 30)
//     // private int unitTypeId;

//     @Column(name = "pro_name_kh",length = 150 ,unique = true)
   
//     private String proNameKh;

//     @Column(name = "pro_name_en",length = 150 , unique = true)
 
//     private String proNameEn;

//     @Column(precision = 10, scale = 2,name = "cost")
//     private BigDecimal cost = new BigDecimal(0);

//     @Column(precision = 10, scale = 2,name = "price")
//     private BigDecimal price = new BigDecimal(0);

//     @Column(name = "product_status",length = 30)
//     private String productStatus;

//     @Column(name = "discount",length = 30)
//     private int discount=0;

//     @Column(name = "status")
//     private boolean status = true;

//     @Column(name = "is_deleted")
//     private boolean isDeleted = false;

//     @CreationTimestamp
//     @Column(updatable = false,name = "create_date")
//     private Date createDate;

//     @Column(name = "create_by")
//     private int createBy;

// }
