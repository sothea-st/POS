package com.example.pos.util.exception.customeException;

public class JavaDataAlreadyExists extends RuntimeException{
    public JavaDataAlreadyExists(){

    }
    public JavaDataAlreadyExists(String msg){
        super(msg);
    }
}
