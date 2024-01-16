package com.example.pos.entity.models;
import java.math.*;
public class ProductQtyAndPrice {
    private int qty;
    private BigDecimal price;

    public ProductQtyAndPrice(){}

    public ProductQtyAndPrice(int qty , BigDecimal price){
        this.price = price;
        this.qty = qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
    public int getQty(){
        return qty;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public BigDecimal getPrice(){
        return price;
    }

}
