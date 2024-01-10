package com.example.pos.entity.models;
import java.math.*;
public class SummerySale {
    private String title;
    private int qty;
    private BigDecimal total;

    public SummerySale(){

    }

    public SummerySale(String title , int qty , BigDecimal total) {
        this.title = title;
        this.qty = qty ;
        this.total = total;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle(){
        return title;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
    public int getQTy(){
        return qty;
    }

    public BigDecimal getTotal(){
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }


}
