package com.example.pos.entity.models;

public class ProductReturn {
    private int proId;
    private int qty;
    public ProductReturn(){}
    public ProductReturn(int proId , int qty) {
        this.proId = proId;
        this.qty = qty;
    }

    public void setProId(int proId){
        this.proId = proId;
    }

    public int getProId(){
        return proId;
    }

    public void setQty(int qty){
        this.qty = qty;
    }
    public int getQty(){
        return qty;
    }

}
