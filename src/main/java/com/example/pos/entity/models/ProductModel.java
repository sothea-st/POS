package com.example.pos.entity.models;

import java.math.BigDecimal;

public class ProductModel {
    private int brandId;
    private String proNameKh;
    private String proImageName;
    private String productStatus;
    private String proNameEn;
    private int id;
    private String flag;
    private int discount;
    private double cost;
    private double price;
    private String weight;
    private String barcode;
    private int catID;
    private String codeExpired;
    private String codeOutStock;
    private int qty;

    public ProductModel() {
    }

    public ProductModel(
            int brandId,
            String proNameKh,
            String proImageName,
            String productStatus,
            String proNameEn,
            int id,
            String flag,
            int discount,
            double cost,
            double price,
            String weight,
            String barcode,
            int catID,
            String codeExpired,
            String codeOutStock,
            int qty) {
        this.brandId = brandId;
        this.proNameKh = proNameKh;
        this.proImageName = proImageName;
        this.productStatus = productStatus;
        this.proNameEn = proNameEn;
        this.id = id;
        this.flag = flag;
        this.discount = discount;
        this.cost = cost;
        this.price = price;
        this.weight = weight;
        this.barcode = barcode;
        this.catID = catID;
        this.codeExpired = codeExpired;
        this.codeOutStock = codeOutStock;
        this.qty = qty;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getProNameKh() {
        return proNameKh;
    }

    public void setProNameKh(String proNameKh) {
        this.proNameKh = proNameKh;
    }

    public String getProImageName() {
        return proImageName;
    }

    public void setProImageName(String proImageName) {
        this.proImageName = proImageName;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    public String getProNameEn() {
        return proNameEn;
    }

    public void setProNameEn(String proNameEn) {
        this.proNameEn = proNameEn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getCatID() {
        return catID;
    }

    public void setCatID(int catID) {
        this.catID = catID;
    }

    public String getCodeExpired() {
        return codeExpired;
    }

    public void setCodeExpired(String codeExpired) {
        this.codeExpired = codeExpired;
    }

    public String getCodeOutStock() {
        return codeOutStock;
    }

    public void setCodeOutStock(String codeOutStock) {
        this.codeOutStock = codeOutStock;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
